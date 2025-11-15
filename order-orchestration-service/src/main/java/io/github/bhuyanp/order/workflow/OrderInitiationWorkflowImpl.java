package io.github.bhuyanp.order.workflow;

import io.github.bhuyanp.order.activity.OrderInitiationActivity;
import io.github.bhuyanp.order.dto.CreateOrderRequest;
import io.github.bhuyanp.order.client.model.Order;
import io.temporal.activity.ActivityOptions;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.api.enums.v1.ParentClosePolicy;
import io.temporal.common.RetryOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Async;
import io.temporal.workflow.ChildWorkflowOptions;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static io.github.bhuyanp.order.workflow.OrderProcessingWorkflowManager.*;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 */
@Slf4j
@WorkflowImpl(taskQueues = ORDER_INITIATION_TASK_QUEUE)
public class OrderInitiationWorkflowImpl implements OrderInitiationWorkflow {
    private final OrderInitiationActivity orderInitiationActivity = Workflow.newActivityStub(
            OrderInitiationActivity.class,
            ActivityOptions.newBuilder()
                    .setRetryOptions(RetryOptions.newBuilder()
                            .setMaximumAttempts(3).build())
                    .setStartToCloseTimeout(Duration.ofSeconds(10))
                    .build()
    );

    @Override
    public Order createOrder(CreateOrderRequest createOrderRequest) {
        Order order = orderInitiationActivity.createOrder(createOrderRequest);

        // Start the child flow asynchronously
        ChildWorkflowOptions options =
                ChildWorkflowOptions.newBuilder()
                        .setParentClosePolicy(ParentClosePolicy.PARENT_CLOSE_POLICY_ABANDON)
                        .setTaskQueue(ORDER_FULFILLMENT_TASK_QUEUE)
                        .setWorkflowId(ORDER_WORKFLOW_ID_PREFIX + order.getOrderId())
                        .build();
        OrderFulfillmentWorkflow child = Workflow.newChildWorkflowStub(OrderFulfillmentWorkflow.class, options);
        Async.procedure(child::processOrder, order);
        Promise<WorkflowExecution> childExecution = Workflow.getWorkflowExecution(child);

        // Wait for child flow to start
        childExecution.get();
        return order;
    }
}
