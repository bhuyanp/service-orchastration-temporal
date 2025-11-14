package io.github.bhuyanp.order.controller;

import io.github.bhuyanp.order.dto.CreateOrderRequest;
import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/order")
@Tag(name = "Order Service")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create Order", description = "Creates an order and returns order id.")
    @ApiResponse(responseCode = "400", description = "Invalid request.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "500", description = "Unable to create order.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<Order> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) throws InterruptedException {
        // Random failures
        int random = new Random().nextInt(5);
        if (random == 0) {
            throw new RuntimeException("Failed to create order");
        }
        Order order = orderService.addOrder(createOrderRequest);
        log.info("Order created with id {}", order.orderId());
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update Order", description = "Update an order.")
    @ApiResponse(responseCode = "404", description = "Order not found.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "500", description = "Unable to update order.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<Void> updateOrder(@RequestBody @Valid Order order) throws InterruptedException {
        log.info("Order updated with id {}", order.orderId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Order By Id", description = "Update an order.")
    @ApiResponse(responseCode = "404", description = "Order not found.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "500", description = "Unable to fetch order.", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<Order> getOrder(@PathVariable UUID orderId) throws InterruptedException {
        Order order = new Order(orderId, null, null, null, null);
        log.info("Order found: {}", order);
        return ResponseEntity.ok(order);
    }
}
