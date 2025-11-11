package io.github.bhuyanp.payment.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ProcessPaymentResponse(
        @NotBlank
        UUID paymentRefId
) {
}
