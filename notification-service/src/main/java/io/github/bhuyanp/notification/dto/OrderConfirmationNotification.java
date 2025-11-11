package io.github.bhuyanp.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Prasanta Bhuyan
 */
public record OrderConfirmationNotification(
        @NotNull
        UUID orderId,
        @NotNull
        LocalDateTime orderPlaced,
        @NotBlank
        String customerId
) {


}
