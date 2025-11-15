package io.github.bhuyanp.order.activity;

import io.github.bhuyanp.order.dto.CreateOrderRequest;
import io.github.bhuyanp.order.client.model.Order;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface OrderInitiationActivity {
    Order createOrder(CreateOrderRequest createOrderRequest);
}
