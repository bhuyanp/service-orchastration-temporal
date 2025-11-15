package io.github.bhuyanp.order.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static io.github.bhuyanp.order.util.CommonEnvironmentUtil.ACTIVE_PROFILE.*;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 * @Date 11/13/25
 */
@Component
@RequiredArgsConstructor
public class CommonEnvironmentUtil implements EnvironmentalUtil {

    private final Environment environment;

    private ACTIVE_PROFILE activeProfile = null;

    @PostConstruct
    public void init() {
        val activeProfiles = Arrays.stream(values()).map(Objects::toString).toList();
        activeProfile = Stream.concat(
                        Arrays.stream(environment.getActiveProfiles()),
                        Arrays.stream(environment.getDefaultProfiles())
                )
                .map(String::toUpperCase)
                .filter(activeProfiles::contains)
                .findFirst().map(ACTIVE_PROFILE::valueOf)
                .orElseThrow(() -> new IllegalStateException("No active profile provided."));
    }

    @Override
    public boolean isProduction() {
        return PROD == activeProfile;
    }

    @Override
    public boolean isDevelopment() {
        return DEVL == activeProfile;
    }

    @Override
    public boolean isLocal() {
        return LOCAL == activeProfile;
    }

    @Override
    public String activeProfile() {
        return activeProfile.toString();
    }

    enum ACTIVE_PROFILE {
        LOCAL, DEVL, ACPT, PROD
    }
}
