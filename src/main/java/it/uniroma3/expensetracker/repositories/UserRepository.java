package it.uniroma3.expensetracker.repositories;

import it.uniroma3.expensetracker.domain.User;
import it.uniroma3.expensetracker.exceptions.EtAuthException;

/**
 * UserRepository interface for performing CRUD operations on the User domain.
 * It defines methods for creating a user, finding a user by email and password,
 * counting users by email, and finding a user by ID.
 */
public interface UserRepository {

    /**
     * Creates a new user in the database.
     *
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The ID of the newly created user.
     * @throws EtAuthException If there is an issue during user creation.
     */
    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    /**
     * Finds a user by their email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The User object if found.
     * @throws EtAuthException If the user is not found or the credentials are incorrect.
     */
    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    /**
     * Gets the count of users with the given email.
     *
     * @param email The email to count.
     * @return The number of users with the specified email.
     */
    Integer getCountByEmail(String email);

    /**
     * Finds a user by their ID.
     *
     * @param userId The ID of the user.
     * @return The User object if found.
     */
    User findById(Integer userId);
}
