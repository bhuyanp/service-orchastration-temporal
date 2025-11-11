package io.github.bhuyanp.shipping.client.config;

import io.github.bhuyanp.shipping.client.api.ShippingServiceApi;
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
@ConditionalOnProperty(prefix = "api", name = "shipping.endpoint")
public class ShippingClientConfig {

    @Value("${api.shipping.endpoint}")
    private String apiEndpoint;

    @Bean
    public ShippingServiceApi shippingServiceApi(WebClient.Builder builder) {
        WebClient webClient = builder
                .baseUrl(apiEndpoint)
                .build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();
        return httpServiceProxyFactory.createClient(ShippingServiceApi.class);
    }
}
