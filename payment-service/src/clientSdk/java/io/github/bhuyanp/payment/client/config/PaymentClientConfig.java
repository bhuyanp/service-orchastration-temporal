package io.github.bhuyanp.payment.client.config;

import io.github.bhuyanp.payment.client.api.PaymentServiceApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@AutoConfiguration
@ConditionalOnProperty(prefix = "api", name = "payment.endpoint")
public class PaymentClientConfig {

    @Value("${api.payment.endpoint}")
    private String apiEndpoint;

    @Bean
    public PaymentServiceApi paymentServiceApi(WebClient.Builder builder) {
        WebClient webClient = builder
                .baseUrl(apiEndpoint)
                .build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();
        return httpServiceProxyFactory.createClient(PaymentServiceApi.class);
    }
}
