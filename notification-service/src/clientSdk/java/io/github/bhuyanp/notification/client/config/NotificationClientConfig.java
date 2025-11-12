package io.github.bhuyanp.notification.client.config;

import io.github.bhuyanp.notification.client.api.OrderNotificationApi;
import io.github.bhuyanp.notification.client.exception.NotificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ProblemDetail;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.function.Function;

@Configuration
@AutoConfiguration
@ConditionalOnProperty(prefix = "api", name = "notification.endpoint")
public class NotificationClientConfig {

    @Value("${api.notification.endpoint}")
    private String apiEndpoint;

    @Bean
    public OrderNotificationApi notificationServiceApi(WebClient.Builder builder) {
        Function<ClientResponse, Mono<? extends Throwable>> EXCEPTION_HANDLER = clientResponse ->
                clientResponse.bodyToMono(ProblemDetail.class)
                        .flatMap(problemDetail -> {
                            // Custom error handling logic
                            return Mono.error(new NotificationException(problemDetail));
                        });
        WebClient webClient = builder
                .baseUrl(apiEndpoint)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection().compress(true)))
//                .defaultStatusHandler(HttpStatusCode::isError, EXCEPTION_HANDLER)
                .build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();
        return httpServiceProxyFactory.createClient(OrderNotificationApi.class);
    }
}
