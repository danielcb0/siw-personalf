package it.uniroma3.expensetracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class to handle authentication-related errors.
 * This exception is thrown when an authentication error occurs, such as invalid login credentials.
 * The exception is annotated with @ResponseStatus to automatically map it to an HTTP status code.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EtAuthException extends RuntimeException {

    /**
     * Constructs a new EtAuthException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public EtAuthException(String message) {
        super(message);
    }
}
