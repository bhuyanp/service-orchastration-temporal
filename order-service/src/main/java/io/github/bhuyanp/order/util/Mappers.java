package io.github.bhuyanp.order.util;

import io.github.bhuyanp.order.dto.CreateOrderRequest;
import io.github.bhuyanp.order.dto.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;

/**
 * @author Prasanta Bhuyan
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Mappers {
    public static final Function<CreateOrderRequest, Order> ORDER_REQ_TO_ORDER = orderRequest -> new Order(UUID.randomUUID(), orderRequest.customer(), orderRequest.orderItems(), orderRequest.payment(), LocalDateTime.now());
}
