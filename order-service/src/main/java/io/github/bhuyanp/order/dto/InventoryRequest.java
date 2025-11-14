package io.github.bhuyanp.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

public record InventoryRequest(
        @NotNull
        UUID orderId,
        @NotEmpty
        List<BlockItem> blockItems
) {

    record BlockItem(
            @NotBlank
            String productId,
            @NotNull
            @Positive
            int quantity
    ) {
    }
}
