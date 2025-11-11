package io.github.bhuyanp.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Order(
        @NotBlank
        UUID orderId,
        @NotNull
        Customer customer,
        @NotEmpty
        List<OrderItem> orderItems,
        @NotNull
        Payment payment,
        @NotNull
        LocalDateTime orderPlaced
        ) {
}
