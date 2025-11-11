package io.github.bhuyanp.order.service;

import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.dto.OrderRequest;
import io.github.bhuyanp.order.util.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Prasanta Bhuyan
 */
@Slf4j
@Service
public class OrderService {

    public Order addOrder(OrderRequest orderRequest){
        return Mappers.ORDER_REQ_TO_ORDER.apply(orderRequest);
    }

}
