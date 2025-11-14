package io.github.bhuyanp.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItem(
        @NotNull
        Product product,

        @Positive
        @NotNull
        @Schema(example = "2")
        int quantity
) {
}
