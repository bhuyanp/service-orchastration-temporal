package io.github.bhuyanp.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(
        @NotNull
        Customer customer,

        @NotEmpty
        List<OrderItem> orderItems,

        @NotNull
        Payment payment
) {
}
