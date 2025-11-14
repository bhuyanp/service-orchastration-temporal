package io.github.bhuyanp.order.client.config;

import io.github.bhuyanp.order.client.api.OrderServiceApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.netty.http.client.HttpClient;

@Configuration
@AutoConfiguration
@ConditionalOnProperty(prefix = "api", name = "order.endpoint")
public class OrderClientConfig {

    @Value("${api.order.endpoint}")
    private String apiEndpoint;

    @Bean
    public OrderServiceApi orderServiceApi(WebClient.Builder builder) {
        WebClient webClient = builder
                .baseUrl(apiEndpoint)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection().compress(true)))
                .build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();
        return httpServiceProxyFactory.createClient(OrderServiceApi.class);
    }
}
