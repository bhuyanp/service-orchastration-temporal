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

/**
 * Configuration class that creates a typed HTTP client for the Inventory Service.
 *
 * <p>This configuration is activated only when the property {@code api.inventory.endpoint}
 * is defined. It uses Spring's {@link WebClient} together with {@link HttpServiceProxyFactory}
 * to create a proxy implementation of {@link InventoryServiceApi} that targets the configured
 * API endpoint.</p>
 *
 * @see InventoryServiceApi
 */
@Configuration
@AutoConfiguration
@ConditionalOnProperty(prefix = "api", name = "inventory.endpoint")
public class InventoryClientConfig {

    /**
     * The base URL of the Inventory API (injected from property {@code api.inventory.endpoint}).
     * Example: {@code https://inventory.example.com/api}
     */
    @Value("${api.inventory.endpoint}")
    private String apiEndpoint;

    /**
     * Builds and exposes an {@link InventoryServiceApi} bean backed by a configured {@link WebClient}.
     *
     * <p>The provided {@code WebClient.Builder} is configured with the {@code apiEndpoint} base URL
     * and a Reactor Netty HTTP connector that enables compression. An {@link HttpServiceProxyFactory}
     * is used to create a type-safe HTTP client proxy for the {@link InventoryServiceApi} interface.</p>
     *
     * @param builder a {@link WebClient.Builder} provided by Spring; must not be null
     * @return an implementation of {@link InventoryServiceApi} bound to the configured API endpoint
     */
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
