package io.github.bhuyanp.shipping.controller;

import io.github.bhuyanp.shipping.dto.ShippingRequest;
import io.github.bhuyanp.shipping.dto.ShippingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

/**
 * @author Prasanta Bhuyan
 */
@Slf4j
@RestController
@RequestMapping("/api/shipping")
@Tag(name = "Shipping Service")
public class ShippingController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Ship order", description = "Ship the products in the purchase order and return the tracking id.")
    @ApiResponse(responseCode = "400", description = "Invalid request.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "500", description = "Unable to ship order.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    ResponseEntity<ShippingResponse> shipOrder(@RequestBody @Valid ShippingRequest shippingRequest) {
        MDC.put("orderId", shippingRequest.orderId());
        // Random failures
        int random = new Random().nextInt(3);
        if (random == 0) {
            throw new RuntimeException("Failed to ship the order " + shippingRequest.orderId());
        }
        // Artificial Delay
//        int delay = new Random().nextInt(11);
//        Thread.sleep(Duration.ofSeconds(delay));
        log.info("Shipped the order {}", shippingRequest.orderId());
        MDC.clear();
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShippingResponse(UUID.randomUUID()));
    }


}
