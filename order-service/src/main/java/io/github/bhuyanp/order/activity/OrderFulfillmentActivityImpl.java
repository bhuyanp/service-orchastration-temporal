package io.github.bhuyanp.order.activity;

import io.github.bhuyanp.inventory.client.api.InventoryServiceApi;
import io.github.bhuyanp.inventory.client.model.InventoryRequest;
import io.github.bhuyanp.inventory.client.model.InventoryResponse;
import io.github.bhuyanp.notification.client.api.OrderNotificationApi;
import io.github.bhuyanp.notification.client.model.OrderCompletionNotification;
import io.github.bhuyanp.notification.client.model.OrderConfirmationNotification;
import io.github.bhuyanp.notification.client.model.OrderFailureNotification;
import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.event.Topics;
import io.github.bhuyanp.order.event.dto.ShippingEvent;
import io.github.bhuyanp.order.service.OrderService;
import io.github.bhuyanp.payment.client.api.PaymentServiceApi;
import io.github.bhuyanp.payment.client.model.PaymentRequest;
import io.github.bhuyanp.payment.client.model.ProcessPaymentResponse;
import io.github.bhuyanp.shipping.client.api.ShippingServiceApi;
import io.github.bhuyanp.shipping.client.model.ShippingRequest;
import io.github.bhuyanp.shipping.client.model.ShippingResponse;
import io.temporal.spring.boot.ActivityImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

import static io.github.bhuyanp.order.util.Mappers.*;

/**
 * @author Prasanta Bhuyan
 */
@Slf4j
@Component
@ActivityImpl(taskQueues = "OrderFulfillmentTaskQueue")
@RequiredArgsConstructor
public class OrderFulfillmentActivityImpl implements OrderFulfillmentActivity {
    private final OrderNotificationApi orderNotificationApi;
    private final InventoryServiceApi inventoryServiceApi;
    private final PaymentServiceApi paymentServiceApi;
    private final ShippingServiceApi shippingServiceApi;
    private final KafkaTemplate<UUID, ShippingEvent> shippingRequestKafkaTemplate;

    @Override
    public void sendOrderConfirmationNotification(Order order) {
        log.info("➡️ SendOrderConfirmationNotification invoked");
        orderNotificationApi.sendOrderConfirmationNotification(ORDER_TO_ORDER_CONFIRMATION_NOTIFICATION_REQUEST.apply(order));
    }

    @Override
    public ProcessPaymentResponse processPayment(Order order) {
        log.info("➡️ ProcessPayment invoked");
        return paymentServiceApi.processPayment(ORDER_TO_PAYMENT_REQUEST.apply(order)).getBody();
    }

    @Override
    public void refundPayment(Order order) {
        log.info("⬅️ RefundPayment invoked");
        paymentServiceApi.refundPayment(ORDER_TO_PAYMENT_REQUEST.apply(order));
    }

    @Override
    public InventoryResponse reserveInventory(Order order) {
        log.info("➡️ ReserveInventory invoked");
        return inventoryServiceApi.blockInventory(ORDER_TO_INVENTORY_REQUEST.apply(order)).getBody();
    }

    @Override
    public void restockInventory(Order order) {
        log.info("⬅️ RestockInventory invoked");
        inventoryServiceApi.releaseInventory(order.orderId().toString());
    }

    @Override
    public ShippingResponse shipOrder(Order order) {
        log.info("➡️ ShipOrder invoked");
        return shippingServiceApi.shipOrder(ORDER_TO_SHIPPING_REQ.apply(order)).getBody();
    }

    @Override
    public void sendShippingMessage(Order order) {
        log.info("➡️ SendShippingMessage invoked");
        int partition = new Random().nextInt(4);
        log.info("SendShippingMessage sent to topic {} and partition {}", Topics.TOPIC_ORDER_SHIPPING_REQUESTED, partition);
        shippingRequestKafkaTemplate.send(Topics.TOPIC_ORDER_SHIPPING_REQUESTED, partition, order.orderId(), ORDER_TO_SHIPPING_EVENT.apply(order));
    }


    @Override
    public void sendOrderCompletionNotification(Order order, UUID trackingId) {
        log.info("➡️ SendOrderCompletionNotification invoked");
        orderNotificationApi.sendOrderCompletionNotification(ORDER_TRACKING_TO_COMPLETION_NOTIFICATION.apply(order, trackingId));
    }

    @Override
    public void sendOrderFailureNotification(OrderFailureNotification orderFailureNotification) {
        log.info("⬅️ SendOrderFailureNotification invoked");
        orderNotificationApi.sendOrderFailureNotification(orderFailureNotification);
    }
}
