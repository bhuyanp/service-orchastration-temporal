package io.github.bhuyanp.order.util;

import io.github.bhuyanp.inventory.client.model.BlockItem;
import io.github.bhuyanp.inventory.client.model.InventoryRequest;
import io.github.bhuyanp.notification.client.model.OrderCompletionNotification;
import io.github.bhuyanp.notification.client.model.OrderConfirmationNotification;
import io.github.bhuyanp.notification.client.model.OrderFailureNotification;
import io.github.bhuyanp.order.client.model.*;
import io.github.bhuyanp.order.dto.CreateOrderRequest;
import io.github.bhuyanp.order.event.dto.ShippingEvent;
import io.github.bhuyanp.payment.client.model.PaymentRequest;
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
    public static final Function<Order, OrderConfirmationNotification> ORDER_TO_ORDER_CONFIRMATION_NOTIFICATION_REQUEST = order -> new OrderConfirmationNotification(order.getOrderId(), order.getOrderPlaced(), order.getCustomer().getId());

    public static final Function<Order, InventoryRequest> ORDER_TO_INVENTORY_REQUEST = order -> new InventoryRequest(
            order.getOrderId(),
            order.getOrderItems().stream().map(it -> new BlockItem(it.getProduct().getId(), it.getQuantity())).toList()
    );

    public static final Function<Order, PaymentRequest> ORDER_TO_PAYMENT_REQUEST = order -> new PaymentRequest(
            order.getOrderId(),
            12.21,
            order.getCustomer().getId()
    );

    public static final Function<Order, ShippingEvent> ORDER_TO_SHIPPING_EVENT = order -> new ShippingEvent(
            order.getOrderId(),
            order.getOrderItems().stream().map(it -> new ShippingEvent.ShippingLineItem(it.getProduct().getId(), it.getQuantity())).toList(),
            order.getCustomer().getId()
    );

    public static final BiFunction<Order, UUID, OrderCompletionNotification> ORDER_TRACKING_TO_COMPLETION_NOTIFICATION
            = (order, trackingId) -> new OrderCompletionNotification(order.getOrderId(), trackingId, order.getOrderPlaced(), LocalDateTime.now(), order.getCustomer().getId());

    public static final Function<Order, OrderFailureNotification> ORDER_TO_FAILURE_NOTIFICATION
            = order -> new OrderFailureNotification(order.getOrderId(), order.getOrderPlaced(), order.getCustomer().getId(), OrderFailureNotification.FailureReasonEnum.PAYMENT_FAILURE);

    public static final Function<CreateOrderRequest, io.github.bhuyanp.order.client.model.CreateOrderRequest> CREATE_ORDER_REQ_DTO_TO_MODEL
            = createOrderRequest -> new io.github.bhuyanp.order.client.model.CreateOrderRequest()
            .customer(new Customer()
                    .id(createOrderRequest.customer().id())
                    .name(createOrderRequest.customer().name())
                    .email(createOrderRequest.customer().email())
            )
            .payment(new Payment()
                    .nameOnTheCard(createOrderRequest.payment().nameOnTheCard())
                    .creditCardNumber(createOrderRequest.payment().creditCardNumber())
                    .expiryMonth(createOrderRequest.payment().expiryMonth())
                    .expiryYear(createOrderRequest.payment().expiryYear())
                    .ccv(createOrderRequest.payment().ccv())
            )
            .orderItems(createOrderRequest.orderItems().stream()
                    .map(orderItem -> new OrderItem()
                            .quantity(orderItem.quantity())
                            .product(new Product(orderItem.product().id()))
                    )
                    .toList()
            );

}
