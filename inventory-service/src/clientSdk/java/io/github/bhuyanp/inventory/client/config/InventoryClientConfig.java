package io.github.bhuyanp.inventory.client.config;

import io.github.bhuyanp.inventory.client.api.InventoryServiceApi;
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
@ConditionalOnProperty(prefix = "api", name = "inventory.endpoint")
public class InventoryClientConfig {

    @Value("${api.inventory.endpoint}")
    private String apiEndpoint;

    @Bean
    public InventoryServiceApi inventoryServiceApi(WebClient.Builder builder) {
        WebClient webClient = builder
                .baseUrl(apiEndpoint)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection().compress(true)))
                .build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();
        return httpServiceProxyFactory.createClient(InventoryServiceApi.class);
    }
}
