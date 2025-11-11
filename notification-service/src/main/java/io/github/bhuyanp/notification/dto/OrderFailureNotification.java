package io.github.bhuyanp.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Prasanta Bhuyan
 */
public record OrderFailureNotification(
        @NotNull
        UUID orderId,
        @NotNull
        LocalDateTime orderPlaced,
        @NotBlank
        String customerId,
        @NotNull
        FAILURE_REASON failureReason
) {
        enum FAILURE_REASON{
                PAYMENT_FAILURE, INSUFFICIENT_INVENTORY
        }
}
