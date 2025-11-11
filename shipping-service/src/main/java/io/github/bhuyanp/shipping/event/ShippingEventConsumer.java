package io.github.bhuyanp.shipping.event;

import io.github.bhuyanp.order.event.STATUS;
import io.github.bhuyanp.order.event.Topics;
import io.github.bhuyanp.order.event.dto.ShippingCompletionEvent;
import io.github.bhuyanp.order.event.dto.ShippingEvent;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 * @Date 11/9/25
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ShippingEventConsumer {

    private final KafkaTemplate<UUID, ShippingCompletionEvent> kafkaTemplate;


    @KafkaListener(topics = Topics.TOPIC_ORDER_SHIPPING_REQUESTED, groupId = "shipping-event-processing-grp")
    public void processShippingRequest(ShippingEvent shippingEvent) throws InterruptedException {
        MDC.put("orderId", shippingEvent.orderId());

        log.info("Shipping event received: {}", shippingEvent);
        STATUS status = STATUS.SUCCESS;
        UUID trackingId = UUID.randomUUID();

        // Artificial Delay
        int delay = new Random().nextInt(2, 12);
        Thread.sleep(Duration.ofSeconds(delay));

        // Random failures
        int random = new Random().nextInt(3);
        if (random == 0) {
            status = STATUS.FAILURE;
            trackingId = null;
        }
        ShippingCompletionEvent shippingCompletionEvent = new ShippingCompletionEvent(shippingEvent.orderId(), trackingId, status);
        kafkaTemplate.send(Topics.TOPIC_ORDER_SHIPPING_COMPLETED, shippingEvent.orderId(), shippingCompletionEvent);
        log.info("Order Shipping completed : {}", shippingCompletionEvent);
        MDC.clear();
    }
}
