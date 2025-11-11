package io.github.bhuyanp.inventory.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record InventoryResponse(
        @NotBlank
        UUID inventoryRefId
) {
}
