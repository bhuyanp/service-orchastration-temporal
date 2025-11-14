package io.github.bhuyanp.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigInteger;

/**
 * @author Prasanta Bhuyan
 */
public record Payment(
        @Schema(example = "Prasanta Bhuyan")
        @NotBlank
        String nameOnTheCard,

        @Schema(example = "1234234534564567")
        @Digits(integer = 16, fraction = 0)
        BigInteger creditCardNumber,

        @Schema(example = "01")
        @Min(1)
        @Max(12)
        int expiryMonth,

        @Schema(example = "2026")
        @Min(2025)
        @Digits(integer = 4, fraction = 0)
        int expiryYear,

        @Schema(example = "999")
        @Min(99)
        @Max(999)
        int ccv
) {

}
