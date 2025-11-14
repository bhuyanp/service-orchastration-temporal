package io.github.bhuyanp.order.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 * @Date 11/8/25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Topics {
    public static final String TOPIC_ORDER_SHIPPING_REQUESTED = "order.shipping.requested";
    public static final String TOPIC_ORDER_SHIPPING_COMPLETED = "order.shipping.completed";
}
