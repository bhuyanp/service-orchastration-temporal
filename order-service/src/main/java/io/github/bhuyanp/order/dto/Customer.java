package io.github.bhuyanp.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record Customer(
        @NotBlank
        @Schema(example = "bhuyanp")
        String id,
        @NotBlank
        @Schema(example = "Prasanta Bhuyan")
        String name,
        @NotBlank
        @Schema(example = "pbhuyan@gmail.com")
        String email
) {
}
