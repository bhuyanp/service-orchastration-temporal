package io.github.bhuyanp.shipping.event;

import io.github.bhuyanp.order.event.STATUS;
import io.github.bhuyanp.order.event.Topics;
import io.github.bhuyanp.order.event.dto.ShippingCompletionEvent;
import io.github.bhuyanp.order.event.dto.ShippingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.jboss.logging.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 * @Date 11/9/25
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ShippingEventConsumer {

    private final KafkaTemplate<UUID, ShippingCompletionEvent> kafkaTemplate;


    @KafkaListener(topics = Topics.TOPIC_ORDER_SHIPPING_REQUESTED, groupId = "shipping-event-listener")
    public void processShippingRequest(ShippingEvent shippingEvent, Acknowledgment acknowledgment) throws InterruptedException {
        try {
            MDC.put("orderId", shippingEvent.orderId());

            log.info("Shipping event received: {}", shippingEvent);
            STATUS status = STATUS.SUCCESS;
            UUID trackingId = UUID.randomUUID();

            // Artificial Delay
            int delay = new Random().nextInt(20, 60);
            Thread.sleep(Duration.ofSeconds(delay));

            // Random failures
            int random = new Random().nextInt(5);
            if (random == 0) {
                status = STATUS.FAILURE;
                trackingId = null;
            }
            ShippingCompletionEvent shippingCompletionEvent = new ShippingCompletionEvent(shippingEvent.orderId(), trackingId, status);
            CompletableFuture<SendResult<UUID, ShippingCompletionEvent>> messagePostFuture = kafkaTemplate.send(Topics.TOPIC_ORDER_SHIPPING_COMPLETED, shippingEvent.orderId(), shippingCompletionEvent);
            messagePostFuture.whenComplete((result, ex) -> {
                if (ex == null) {
                    RecordMetadata recordMetadata = result.getRecordMetadata();
                    log.info("Shipping complete for order {} posted to {}:{}:{}", shippingCompletionEvent.orderId(), recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
                }
            });
            log.info("Order Shipping completed : {}", shippingCompletionEvent);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("Error processing shipping request.", e);
        } finally {
            MDC.clear();
        }
    }
}
