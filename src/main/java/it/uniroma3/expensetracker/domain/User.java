package it.uniroma3.expensetracker.domain;

/**
 * The User class represents a user in the Expense Tracker application.
 * Each user has a unique identifier, first name, last name, email, and password.
 */
public class User {

    // Fields representing the properties of the user
    private Integer userId;    // Unique identifier for the user
    private String firstName;  // First name of the user
    private String lastName;   // Last name of the user
    private String email;      // Email address of the user
    private String password;   // Password for the user's account

    /**
     * Constructs a new User with the specified details.
     *
     * @param userId    the unique identifier for the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param email     the email address of the user
     * @param password  the password for the user's account
     */
    public User(Integer userId, String firstName, String lastName, String email, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // Getter and setter methods for each field

    /**
     * Gets the unique identifier for the user.
     *
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier for the user.
     *
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets the first name of the user.
     *
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password for the user's account.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user's account.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
