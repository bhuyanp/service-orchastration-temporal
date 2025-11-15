package io.github.bhuyanp.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItem(
        @NotNull
        @Valid
        Product product,

        @Positive
        @NotNull
        @Schema(example = "2")
        int quantity
) {
}
