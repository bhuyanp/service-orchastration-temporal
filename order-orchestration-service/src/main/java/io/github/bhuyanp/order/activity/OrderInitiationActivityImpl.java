package io.github.bhuyanp.order.activity;

import io.github.bhuyanp.order.client.api.OrderServiceApi;
import io.github.bhuyanp.order.client.model.CreateOrderRequest;
import io.github.bhuyanp.order.client.model.Order;
import io.temporal.spring.boot.ActivityImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 * @Date 11/10/25
 */
@Slf4j
@Component
@ActivityImpl(taskQueues = "OrderInitiationTaskQueue")
@RequiredArgsConstructor
public class OrderInitiationActivityImpl implements OrderInitiationActivity {
    private final OrderServiceApi orderServiceApi;

    @Override
    public Order createOrder(CreateOrderRequest createOrderRequest) {
        return orderServiceApi.createOrder(createOrderRequest).getBody();
    }


}
