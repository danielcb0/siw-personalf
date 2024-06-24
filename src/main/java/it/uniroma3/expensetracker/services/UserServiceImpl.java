package it.uniroma3.expensetracker.services;

import it.uniroma3.expensetracker.domain.User;
import it.uniroma3.expensetracker.exceptions.EtAuthException;
import it.uniroma3.expensetracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

/**
 * Service implementation for managing user authentication and registration.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Validates the user's credentials and returns the user information.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The validated user.
     * @throws EtAuthException If the email or password is incorrect.
     */
    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if(email != null) email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

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
    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid email format");
        Integer count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new EtAuthException("Email already in use");
        Integer userId = userRepository.create(firstName, lastName, email, password);
        return userRepository.findById(userId);
    }
}
