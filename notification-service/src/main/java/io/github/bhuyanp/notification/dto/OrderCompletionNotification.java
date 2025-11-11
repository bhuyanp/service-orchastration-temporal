package io.github.bhuyanp.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Prasanta Bhuyan
 */
public record OrderCompletionNotification(
        @NotNull
        UUID orderId,
        @NotNull
        UUID shippingTrackingNumber,
        @NotNull
        LocalDateTime orderPlaced,
        @NotNull
        LocalDateTime orderCompleted,
        @NotBlank
        String customerId
) {


}
