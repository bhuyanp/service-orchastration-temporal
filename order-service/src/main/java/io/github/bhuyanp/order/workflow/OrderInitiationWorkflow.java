package io.github.bhuyanp.order.workflow;

import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.dto.OrderRequest;
import io.github.bhuyanp.order.event.dto.ShippingCompletionEvent;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderInitiationWorkflow {

    @WorkflowMethod
    Order startOrder(OrderRequest orderRequest);

}
