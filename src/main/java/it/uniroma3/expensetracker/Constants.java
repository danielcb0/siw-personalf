package it.uniroma3.expensetracker;

/**
 * This class defines constants used throughout the Expense Tracker application.
 */
public class Constants {

    /**
     * The secret key used for signing JWT tokens.
     * This key should be kept secure and not exposed publicly.
     */
    public static final String API_SECRET_KEY = "expensetrackerapikey";

    /**
     * The validity period of JWT tokens, specified in milliseconds.
     * This example sets the validity to 2 hours (2 * 60 * 60 * 1000 milliseconds).
     */
    public static final long TOKEN_VALIDITY = 2 * 60 * 60 * 1000;
}
