package io.github.bhuyanp.payment.controller;

import io.github.bhuyanp.payment.dto.PaymentRequest;
import io.github.bhuyanp.payment.dto.ProcessPaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

/**
 * @author Prasanta Bhuyan
 */
@Slf4j
@RestController
@RequestMapping("/api/payment")
@Tag(name = "Payment Service")
public class PaymentController {


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Process payment", description = "Processed payment for an order and return a payment confirmation id.")
    @ApiResponse(responseCode = "400", description = "Invalid request.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "500", description = "Unable to process payment.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    ResponseEntity<ProcessPaymentResponse> processPayment(@RequestBody @Valid PaymentRequest paymentRequest) throws InterruptedException {
        // Random failures
        int random = new Random().nextInt(5);
        if (random == 0) {
            throw new RuntimeException("Failed to process payment");
        }
        // Artificial Delay
        int delay = new Random().nextInt(2, 16);
        Thread.sleep(Duration.ofSeconds(delay));
        log.info("Processed payment for order {}", paymentRequest.orderId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProcessPaymentResponse(UUID.randomUUID()));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Refund payment", description = "Refunds payment back based on the payment ref id.")
    @ApiResponse(responseCode = "404", description = "Payment ref id missing.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "500", description = "Unable to refund the payment.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    ResponseEntity<Void> refundPayment(@RequestBody @Valid PaymentRequest paymentRequest) throws InterruptedException {
        // Random failures
        int random = new Random().nextInt(5);
        if (random == 0) {
            throw new RuntimeException("Failed to refund payment");
        }
        // Artificial Delay
        int delay = new Random().nextInt(2, 16);
        Thread.sleep(Duration.ofSeconds(delay));

        log.info("Refunded payment for order {}", paymentRequest.orderId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
