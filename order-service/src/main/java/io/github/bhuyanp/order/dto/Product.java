package io.github.bhuyanp.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record Product(
        @Schema(example = "Product A")
        @NotBlank
        String id
) {
}
