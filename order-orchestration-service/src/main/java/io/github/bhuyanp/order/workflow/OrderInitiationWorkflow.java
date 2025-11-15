package io.github.bhuyanp.order.workflow;

import io.github.bhuyanp.order.dto.CreateOrderRequest;
import io.github.bhuyanp.order.client.model.Order;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderInitiationWorkflow {

    @WorkflowMethod
    Order createOrder(CreateOrderRequest createOrderRequest);

}
