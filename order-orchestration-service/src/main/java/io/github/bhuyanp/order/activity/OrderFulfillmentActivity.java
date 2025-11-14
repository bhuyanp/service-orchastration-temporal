package io.github.bhuyanp.order.activity;

import io.github.bhuyanp.inventory.client.model.InventoryResponse;
import io.github.bhuyanp.order.client.model.Order;
import io.github.bhuyanp.payment.client.model.ProcessPaymentResponse;
import io.temporal.activity.ActivityInterface;

import java.util.UUID;

@ActivityInterface
public interface OrderFulfillmentActivity {

    void sendOrderConfirmationNotification(Order order);

    ProcessPaymentResponse processPayment(Order order);

    void refundPayment(Order order);

    InventoryResponse reserveInventory(Order order);

    void restockInventory(Order order);

    void sendShippingMessage(Order order);

    void sendOrderCompletionNotification(Order order, UUID trackingId);

    void sendOrderFailureNotification(Order order);
}
