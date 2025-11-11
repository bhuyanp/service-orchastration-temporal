package io.github.bhuyanp.shipping.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ShippingResponse(
        @NotBlank
        UUID trackingId
) {
}
