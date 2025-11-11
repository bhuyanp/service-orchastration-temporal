package io.github.bhuyanp.notification.controller;

import io.github.bhuyanp.notification.dto.OrderCompletionNotification;
import io.github.bhuyanp.notification.dto.OrderConfirmationNotification;
import io.github.bhuyanp.notification.dto.OrderFailureNotification;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Random;

/**
 * @author Prasanta Bhuyan
 */
@Slf4j
@RestController
@RequestMapping("/api/notification/order")
@Tag(name = "Order Notification", description = "Order management related notification APIs")
public class OrderNotificationController {

    @PostMapping(path = "/confirmation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendOrderConfirmationNotification(@Valid @RequestBody OrderConfirmationNotification orderConfirmationNotification) throws InterruptedException {
        // Random failures
//        int random = new Random().nextInt(2);
//        if (random==0) {
//            throw new RuntimeException("Failed to send confirmation notification");
//        }
        // Artificial Delay
        int delay = new Random().nextInt(20);
        Thread.sleep(Duration.ofSeconds(delay));
        log.info("Confirmation email sent for :{}", orderConfirmationNotification);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PostMapping(path = "/completion", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendOrderCompletionNotification(@Valid @RequestBody OrderCompletionNotification orderCompletionNotification) throws InterruptedException {
        // Random failures
//        int random = new Random().nextInt(2);
//        if (random == 0) {
//            throw new RuntimeException("Failed to send order completion notification");
//        }
        // Artificial Delay
        int delay = new Random().nextInt(20);
        Thread.sleep(Duration.ofSeconds(delay));
        log.info("Order completion email sent for :{}", orderCompletionNotification);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(path = "/failure", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendOrderFailureNotification(@Valid @RequestBody OrderFailureNotification orderFailureNotification) throws InterruptedException {
        // Random failures
//        int random = new Random().nextInt(2);
//        if (random == 0) {
//            throw new RuntimeException("Failed to send failure notification");
//        }
        // Artificial Delay
        int delay = new Random().nextInt(4);
        Thread.sleep(Duration.ofSeconds(delay));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
