package io.github.bhuyanp.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record PaymentRequest(
        @NotNull
        UUID orderId,
        @Positive
        @NotNull
        Double orderTotal,
        @NotBlank
        String customerId
) {
}
