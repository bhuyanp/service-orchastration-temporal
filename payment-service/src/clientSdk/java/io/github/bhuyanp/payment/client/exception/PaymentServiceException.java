package io.github.bhuyanp.payment.client.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
@Getter
@ToString
public class PaymentServiceException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final ProblemDetail details;
    public PaymentServiceException(HttpStatus httpStatus, ProblemDetail details) {
        super(details.getTitle());
        this.httpStatus = httpStatus;
        this.details = details;
    }
}
