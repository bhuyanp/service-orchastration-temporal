package io.github.bhuyanp.notification.client.exception;

import org.springframework.http.ProblemDetail;

/**
 * @author <a href="mailto:prasanta.k.bhuyan@gmail.com">Prasanta Bhuyan</a>
 * @Date 11/11/25
 */
public class NotificationException extends RuntimeException {
    private final ProblemDetail problemDetail;

    public NotificationException(ProblemDetail problemDetail) {
        this.problemDetail = problemDetail;
    }

    public ProblemDetail getProblemDetail() {
        return problemDetail;
    }
}
