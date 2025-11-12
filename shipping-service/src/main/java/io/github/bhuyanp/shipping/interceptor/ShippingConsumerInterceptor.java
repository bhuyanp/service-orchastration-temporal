package io.github.bhuyanp.shipping.interceptor;

import io.github.bhuyanp.order.event.dto.ShippingCompletionEvent;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;
import java.util.UUID;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 * @Date 11/11/25
 */
public class ShippingConsumerInterceptor implements ConsumerInterceptor<UUID, ShippingCompletionEvent> {

    @Override
    public ConsumerRecords<UUID, ShippingCompletionEvent> onConsume(ConsumerRecords<UUID, ShippingCompletionEvent> records) {
        return null;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
