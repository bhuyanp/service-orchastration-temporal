package io.github.bhuyanp.order.controller;

import io.github.bhuyanp.order.client.model.CreateOrderRequest;
import io.github.bhuyanp.order.client.model.Order;
import io.github.bhuyanp.order.workflow.OrderProcessingWorkflowManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Prasanta Bhuyan
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderProcessingWorkflowManager workflowManager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> processOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        Order order = workflowManager.start(createOrderRequest);
        MDC.put("orderId", order.getOrderId().toString());
        log.info("New order created: {}", order);
        return ResponseEntity.ok("Order received. Order id is " + order.getOrderId());
    }

    @GetMapping(path = "/{orderId}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getWorkflowStatus(@PathVariable UUID orderId) {
        return ResponseEntity.ok(workflowManager.getWorkflowStatus(orderId));
    }

}
