package io.github.bhuyanp.order.util;

import io.github.bhuyanp.inventory.client.model.BlockItem;
import io.github.bhuyanp.inventory.client.model.InventoryRequest;
import io.github.bhuyanp.notification.client.model.OrderCompletionNotification;
import io.github.bhuyanp.notification.client.model.OrderConfirmationNotification;
import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.dto.OrderRequest;
import io.github.bhuyanp.order.event.dto.ShippingEvent;
import io.github.bhuyanp.payment.client.model.PaymentRequest;
import io.github.bhuyanp.shipping.client.model.ShippingItem;
import io.github.bhuyanp.shipping.client.model.ShippingRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Prasanta Bhuyan
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Mappers {
    public static final Function<OrderRequest, Order> ORDER_REQ_TO_ORDER = orderRequest -> new Order(UUID.randomUUID(), orderRequest.customer(), orderRequest.orderItems(), orderRequest.payment(), LocalDateTime.now());
    public static final Function<Order, OrderConfirmationNotification> ORDER_TO_ORDER_CONFIRMATION_NOTIFICATION_REQUEST = order -> new OrderConfirmationNotification(order.orderId(), order.orderPlaced(), order.customer().id());

    public static final Function<Order, InventoryRequest> ORDER_TO_INVENTORY_REQUEST = order -> new InventoryRequest(
            order.orderId(),
            order.orderItems().stream().map(it -> new BlockItem(it.product().id(), it.quantity())).toList()
    );

    public static final Function<Order, PaymentRequest> ORDER_TO_PAYMENT_REQUEST = order -> new PaymentRequest(
            order.orderId(),
            12.21,
            order.customer().id()
    );

    public static final Function<Order, ShippingRequest> ORDER_TO_SHIPPING_REQ = order -> new ShippingRequest(
            order.orderId(),
            order.orderItems().stream().map(it -> new ShippingItem(it.product().id(), it.quantity())).toList(),
            order.customer().id()
    );

    public static final Function<Order, ShippingEvent> ORDER_TO_SHIPPING_EVENT = order -> new ShippingEvent(
            order.orderId(),
            order.orderItems().stream().map(it -> new ShippingEvent.ShippingLineItem(it.product().id(), it.quantity())).toList(),
            order.customer().id()
    );

    public static final BiFunction<Order, UUID, OrderCompletionNotification> ORDER_TRACKING_TO_COMPLETION_NOTIFICATION
            = (order, trackingId) -> new OrderCompletionNotification(order.orderId(), trackingId, order.orderPlaced(), LocalDateTime.now(), order.customer().id());

}
