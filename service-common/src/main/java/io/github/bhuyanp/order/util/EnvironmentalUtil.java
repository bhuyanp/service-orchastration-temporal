package io.github.bhuyanp.order.util;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 * @Date 11/13/25
 */
public interface EnvironmentalUtil {
    boolean isProduction();

    boolean isDevelopment();

    boolean isLocal();

    String activeProfile();
}
