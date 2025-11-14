package io.github.bhuyanp.order.workflow;

import io.github.bhuyanp.order.client.model.Order;
import io.github.bhuyanp.order.event.dto.ShippingCompletionEvent;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderFulfillmentWorkflow {

    @WorkflowMethod
    void processOrder(Order order);

    @SignalMethod
    void processShippingCompletionEvent(ShippingCompletionEvent shippingCompletionEvent);
}
