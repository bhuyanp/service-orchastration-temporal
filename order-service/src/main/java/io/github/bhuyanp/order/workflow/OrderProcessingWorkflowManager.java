package io.github.bhuyanp.order.workflow;

import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.dto.OrderRequest;
import io.github.bhuyanp.order.event.Topics;
import io.github.bhuyanp.order.event.dto.ShippingCompletionEvent;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Prasanta Bhuyan
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderProcessingWorkflowManager {
    static final String ORDER_WORKFLOW_ID_PREFIX = "Order_";
    public static final String ORDER_FULFILLMENT_TASK_QUEUE = "OrderFulfillmentTaskQueue";
    public static final String ORDER_INITIATION_TASK_QUEUE = "OrderInitiationTaskQueue";
    private final WorkflowClient client;

    public Order start(final OrderRequest orderRequest) {
        String workflowId = ORDER_WORKFLOW_ID_PREFIX + orderRequest.customer().id() + "_" + LocalDateTime.now();
        OrderInitiationWorkflow workflow = client.newWorkflowStub(
                OrderInitiationWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue(ORDER_INITIATION_TASK_QUEUE)
                        .setWorkflowId(workflowId)
                        .build());
        log.info("New workflow id: {}", workflowId);
        return workflow.startOrder(orderRequest);
    }

    @KafkaListener(topics = Topics.TOPIC_ORDER_SHIPPING_COMPLETED, groupId = "order-fulfillment-listener")
    public void updateShippingDetails(ShippingCompletionEvent shippingCompletionEvent) {
        try {
            String workflowId = getWorkflowId(shippingCompletionEvent.orderId());
            log.info("Shipping completion message received: {}", shippingCompletionEvent);
            OrderFulfillmentWorkflow workflow = client.newWorkflowStub(OrderFulfillmentWorkflow.class, workflowId);
            workflow.processShippingCompletionEvent(shippingCompletionEvent);
        } catch (Exception e) {
            log.error("Error processing message from topic {}.", Topics.TOPIC_ORDER_SHIPPING_COMPLETED, e);
        }
    }

    public List<String> getWorkflowStatus(UUID orderId) {
        return client.streamHistory(getWorkflowId(orderId)).map(historyEvent -> historyEvent.getActivityTaskScheduledEventAttributes().getActivityType().getName()).filter(StringUtils::hasText).toList();
    }

    private String getWorkflowId(UUID orderId) {
        return ORDER_WORKFLOW_ID_PREFIX + orderId;
    }
}
