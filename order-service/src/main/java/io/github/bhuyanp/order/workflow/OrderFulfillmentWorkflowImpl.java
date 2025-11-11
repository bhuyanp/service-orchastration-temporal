package io.github.bhuyanp.order.workflow;

import io.github.bhuyanp.inventory.client.model.InventoryResponse;
import io.github.bhuyanp.notification.client.model.OrderCompletionNotification;
import io.github.bhuyanp.notification.client.model.OrderConfirmationNotification;
import io.github.bhuyanp.order.activity.OrderFulfillmentActivity;
import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.event.STATUS;
import io.github.bhuyanp.order.event.dto.ShippingCompletionEvent;
import io.github.bhuyanp.payment.client.model.PaymentRequest;
import io.github.bhuyanp.payment.client.model.ProcessPaymentResponse;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.failure.ApplicationFailure;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Saga;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.time.Duration;
import java.time.LocalDateTime;

import static io.github.bhuyanp.order.util.Mappers.*;
import static io.github.bhuyanp.order.workflow.OrderProcessingWorkflowManager.ORDER_FULFILLMENT_TASK_QUEUE;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 */
@Slf4j
@WorkflowImpl(taskQueues = ORDER_FULFILLMENT_TASK_QUEUE)
public class OrderFulfillmentWorkflowImpl implements OrderFulfillmentWorkflow {
    private final OrderFulfillmentActivity orderFulfillmentActivity = Workflow.newActivityStub(
            OrderFulfillmentActivity.class,
            ActivityOptions.newBuilder()
                    .setRetryOptions(RetryOptions.newBuilder()
//                            .setDoNotRetry(NonRetriableNotificationException.class.getName())
                            .setMaximumAttempts(3).build())
                    .setStartToCloseTimeout(Duration.ofSeconds(10))
                    .build()
    );

    private ShippingCompletionEvent shippingCompletionEvent = null;

    @Override
    public void processOrder(Order order) {
        log.info("🚀 Starting order processing for : {}", order);
        Saga saga = new Saga(new Saga.Options.Builder()
//                .setParallelCompensation(parallelCompensations)
                .build());
        try {
            try {
                orderFulfillmentActivity.sendOrderConfirmationNotification(order);
                log.info("✔︎ Order confirmation email sent.");
            } catch (ActivityFailure e) {
                // ignore. Notification is not critical business function
                log.error("SendOrderConfirmationNotification failed.", e);
            }

            // Payment flow
            saga.addCompensation(() -> orderFulfillmentActivity.refundPayment(order));
            final ProcessPaymentResponse processPaymentResponse = orderFulfillmentActivity.processPayment(order);
            log.info("✔︎ Payment processed successfully: {}", processPaymentResponse);
            // Payment flow


            // Payment flow
            saga.addCompensation(() -> orderFulfillmentActivity.restockInventory(order));
            final InventoryResponse inventoryResponse = orderFulfillmentActivity.reserveInventory(order);
            log.info("✔︎ Inventory blocked successfully: {}", inventoryResponse);
            // Payment flow


            // Shipping flow
            orderFulfillmentActivity.sendShippingMessage(order);
            Workflow.await(() -> this.shippingCompletionEvent != null);
            if (shippingCompletionEvent == null) {
                throw new RuntimeException("Order shipping request timed out");
            }
            if (shippingCompletionEvent.status() == STATUS.FAILURE) {
                throw new RuntimeException("Order shipping failed");
            }
            log.info("✔︎ Order shipping successful: {}", shippingCompletionEvent);
            // Shipping flow

            try {
                orderFulfillmentActivity.sendOrderCompletionNotification(order, shippingCompletionEvent.trackingId());
                log.info("✔︎ Order completion email sent.");
            } catch (ActivityFailure e) {
                // ignore. Notification is not critical business function
                log.error("SendOrderCompletionNotification failed.", e);
            }
            log.info("✔︎ Order processing completed for: {}", order);
        } catch (ActivityFailure e) {
            log.error("🚫 Activity failure while processing the order.", e);
            saga.compensate();
            throw e;
        } catch (Exception e) {
            log.error("🚫 Failed to process the order.", e);
            saga.compensate();
            throw ApplicationFailure.newNonRetryableFailure(e.getMessage(), e.getClass().getName(), e.getCause());
        }
    }

    @Override
    public void processShippingCompletionEvent(ShippingCompletionEvent shippingCompletionEvent) {
        log.info("Shipping completion event received: {}", shippingCompletionEvent);
        this.shippingCompletionEvent = shippingCompletionEvent;
    }
}
