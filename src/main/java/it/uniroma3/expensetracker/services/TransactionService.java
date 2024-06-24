package it.uniroma3.expensetracker.services;

import it.uniroma3.expensetracker.domain.Transaction;
import it.uniroma3.expensetracker.exceptions.EtBadRequestException;
import it.uniroma3.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;

/**
 * TransactionService interface defines the business operations related to transactions.
 * This interface will be implemented by a service class to provide the functionality.
 */
public interface TransactionService {

    /**
     * Fetches all transactions for a specific user and category.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category whose transactions are to be fetched.
     * @return A list of transactions.
     */
    List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId);

    /**
     * Fetches a specific transaction by its ID for a specific user and category.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category.
     * @param transactionId The ID of the transaction to be fetched.
     * @return The transaction with the specified ID.
     * @throws EtResourceNotFoundException If the transaction is not found.
     */
    Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

    /**
     * Adds a new transaction for a specific user and category.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category.
     * @param amount The amount of the transaction.
     * @param note A note for the transaction.
     * @param transactionDate The date of the transaction.
     * @return The newly added transaction.
     * @throws EtBadRequestException If there is a problem with the request.
     */
    Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException;

    /**
     * Updates an existing transaction for a specific user and category.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category.
     * @param transactionId The ID of the transaction to be updated.
     * @param transaction The updated transaction data.
     * @throws EtBadRequestException If there is a problem with the request.
     */
    void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;

    /**
     * Removes a transaction by its ID for a specific user and category.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category.
     * @param transactionId The ID of the transaction to be removed.
     * @throws EtResourceNotFoundException If the transaction is not found.
     */
    void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

}
