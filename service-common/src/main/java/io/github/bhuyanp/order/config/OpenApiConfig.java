package io.github.bhuyanp.order.config;

import io.github.bhuyanp.order.util.EnvironmentalUtil;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@ConditionalOnClass(OpenAPI.class)
@SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "Bearer", bearerFormat = "JWT", name = "bearerAuth")
public class OpenApiConfig {

    private final EnvironmentalUtil environmentalUtil;

    @Value("${spring.application.name}")
    private String appName;
    @Value("${spring.application.version}")
    private String appVersion;
    @Value("${spring.application.description:}")
    private String appDescription;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(appName + " [" + environmentalUtil.activeProfile().toUpperCase() + "]")
                        .version(appVersion)
                        .license(new License().name("Prasanta Bhuyan Corp").identifier("License identifier"))
                        .description(appDescription))
                .security(List.of(new SecurityRequirement().addList("bearerAuth")));
    }
}
