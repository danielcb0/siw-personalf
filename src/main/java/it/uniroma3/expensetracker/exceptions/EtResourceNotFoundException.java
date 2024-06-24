package it.uniroma3.expensetracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class to handle resource not found errors.
 * This exception is thrown when a requested resource does not exist.
 * The exception is annotated with @ResponseStatus to automatically map it to an HTTP status code.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EtResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new EtResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public EtResourceNotFoundException(String message) {
        super(message);
    }
}
