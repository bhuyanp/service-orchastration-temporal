package io.github.bhuyanp.shipping.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public record ShippingRequest(
        @NotNull
        UUID orderId,
        @NotEmpty
        List<ShippingItem> shippingItems,
        @NotBlank
        String customerId
) {

    record ShippingItem(
            @NotBlank
            String productId,
            @Positive
            @NotNull
            int quantity
    ){}
}
