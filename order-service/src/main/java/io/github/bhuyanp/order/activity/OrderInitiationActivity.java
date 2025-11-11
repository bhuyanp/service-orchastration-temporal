package io.github.bhuyanp.order.activity;

import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.dto.OrderRequest;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface OrderInitiationActivity {
    Order addOrder(OrderRequest orderRequest);
}
