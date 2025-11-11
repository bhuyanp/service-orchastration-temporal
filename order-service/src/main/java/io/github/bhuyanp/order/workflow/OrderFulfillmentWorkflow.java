package io.github.bhuyanp.order.workflow;

import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.dto.OrderRequest;
import io.github.bhuyanp.order.event.dto.ShippingCompletionEvent;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.UpdateMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderFulfillmentWorkflow {

    @WorkflowMethod
    void processOrder(Order order);

    @SignalMethod
    void processShippingCompletionEvent(ShippingCompletionEvent shippingCompletionEvent);
}
