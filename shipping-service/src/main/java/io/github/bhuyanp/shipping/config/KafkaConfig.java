package io.github.bhuyanp.shipping.config;

import io.github.bhuyanp.order.event.Topics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 * @Date 11/10/25
 */
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topicOrderShippingCompleted() {
        return TopicBuilder.name(Topics.TOPIC_ORDER_SHIPPING_COMPLETED)
                .partitions(4)
                .replicas(1)
                .build();
    }

}
