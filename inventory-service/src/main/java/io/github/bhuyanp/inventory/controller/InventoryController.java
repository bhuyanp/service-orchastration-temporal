package io.github.bhuyanp.inventory.controller;

import io.github.bhuyanp.inventory.dto.InventoryRequest;
import io.github.bhuyanp.inventory.dto.InventoryResponse;
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
@RequestMapping("/api/inventory")
@Tag(name = "Inventory Service")
public class InventoryController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Block inventory", description = "Block the inventory for a given order.")
    @ApiResponse(responseCode = "400", description = "Invalid request.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "500", description = "Unable to block the inventory.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    ResponseEntity<InventoryResponse> blockInventory(@RequestBody @Valid InventoryRequest inventoryRequest) throws InterruptedException {
        // Random failures
        int random = new Random().nextInt(5);
        if (random == 0) {
            throw new RuntimeException("Failed to hold the inventory");
        }
        // Artificial Delay
        int delay = new Random().nextInt(2, 16);
        Thread.sleep(Duration.ofSeconds(delay));
        log.info("Blocked inventory for order {}", inventoryRequest.orderId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new InventoryResponse(UUID.randomUUID()));
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Release inventory hold", description = "Releases the blocked inventory back into the pool.")
    @ApiResponse(responseCode = "404", description = "Order id missing.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "500", description = "Unable to release the inventory.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    ResponseEntity<Void> releaseInventory(@PathVariable String orderId) throws InterruptedException {
        // Random failures
        int random = new Random().nextInt(5);
        if (random == 0) {
            throw new RuntimeException("Failed to release inventory hold");
        }
        // Artificial Delay
        int delay = new Random().nextInt(2, 16);
        Thread.sleep(Duration.ofSeconds(delay));

        log.info("Released inventory for order{}", orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
