package io.github.bhuyanp.order.activity;

import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.dto.OrderRequest;
import io.github.bhuyanp.order.service.OrderService;
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
    private final OrderService orderService;

    @Override
    public Order addOrder(OrderRequest orderRequest) {
        return orderService.addOrder(orderRequest);
    }


}
