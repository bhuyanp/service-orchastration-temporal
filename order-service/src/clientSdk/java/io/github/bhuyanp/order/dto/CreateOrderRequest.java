package io.github.bhuyanp.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderRequest(
        @NotNull
        @Valid
        Customer customer,

        @NotEmpty
        List<@Valid OrderItem> orderItems,

        @NotNull
        @Valid
        Payment payment
) {
}
