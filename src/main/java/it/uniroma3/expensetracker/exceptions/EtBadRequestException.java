package it.uniroma3.expensetracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class to handle bad request errors.
 * This exception is thrown when a client sends an invalid request.
 * The exception is annotated with @ResponseStatus to automatically map it to an HTTP status code.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EtBadRequestException extends RuntimeException {

    /**
     * Constructs a new EtBadRequestException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public EtBadRequestException(String message) {
        super(message);
    }
}
