package io.github.bhuyanp.order.event.dto;

import io.github.bhuyanp.order.event.STATUS;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ShippingCompletionEvent(
        @NotNull
        UUID orderId,
        UUID trackingId,
        @NotNull
        STATUS status
) {
}
