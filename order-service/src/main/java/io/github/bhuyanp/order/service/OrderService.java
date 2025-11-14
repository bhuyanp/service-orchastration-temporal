package io.github.bhuyanp.order.service;

import io.github.bhuyanp.order.dto.CreateOrderRequest;
import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Prasanta Bhuyan
 */
@Slf4j
@Service
public class OrderService {

    public Order addOrder(CreateOrderRequest createOrderRequest) {
        return Mappers.ORDER_REQ_TO_ORDER.apply(createOrderRequest);
    }

}
