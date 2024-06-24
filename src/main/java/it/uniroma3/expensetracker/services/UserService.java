package it.uniroma3.expensetracker.services;

import it.uniroma3.expensetracker.domain.User;
import it.uniroma3.expensetracker.exceptions.EtAuthException;

/**
 * Service interface for managing user authentication and registration.
 */
public interface UserService {

    /**
     * Validates the user's credentials and returns the user information.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The validated user.
     * @throws EtAuthException If the email or password is incorrect.
     */
    User validateUser(String email, String password) throws EtAuthException;

    /**
     * Registers a new user with the provided details.
     *
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The newly registered user.
     * @throws EtAuthException If there is an issue with the registration process.
     */
    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

}
