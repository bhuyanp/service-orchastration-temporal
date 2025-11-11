package io.github.bhuyanp.order.controller;

import io.github.bhuyanp.order.dto.Order;
import io.github.bhuyanp.order.dto.OrderRequest;
import io.github.bhuyanp.order.service.OrderService;
import io.github.bhuyanp.order.workflow.OrderProcessingWorkflowManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Prasanta Bhuyan
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderProcessingWorkflowManager workflowStarter;
    private final OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> processOrder(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = workflowStarter.start(orderRequest);
        MDC.put("orderId", order.orderId().toString());
        log.info("New order created: {}", order);
        return ResponseEntity.ok("Order received. Order id is " + order.orderId());
    }

}
