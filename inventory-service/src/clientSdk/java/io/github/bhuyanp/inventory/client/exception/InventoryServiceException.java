package io.github.bhuyanp.inventory.client.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
@Getter
@ToString
public class InventoryServiceException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final ProblemDetail details;
    public InventoryServiceException(HttpStatus httpStatus, ProblemDetail details) {
        super(details.getTitle());
        this.httpStatus = httpStatus;
        this.details = details;
    }
}
