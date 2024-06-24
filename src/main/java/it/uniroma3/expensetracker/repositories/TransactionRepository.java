package it.uniroma3.expensetracker.repositories;

import it.uniroma3.expensetracker.domain.Transaction;
import it.uniroma3.expensetracker.exceptions.EtBadRequestException;
import it.uniroma3.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;

/**
 * Interface for the Transaction Repository.
 * This interface provides CRUD operations for the Transaction entity.
 */
public interface TransactionRepository {

    /**
     * Fetches all transactions for a given user and category.
     *
     * @param userId The ID of the user whose transactions are to be fetched.
     * @param categoryId The ID of the category to which the transactions belong.
     * @return A list of transactions belonging to the specified user and category.
     */
    List<Transaction> findAll(Integer userId, Integer categoryId);

    /**
     * Fetches a specific transaction by user ID, category ID, and transaction ID.
     *
     * @param userId The ID of the user to whom the transaction belongs.
     * @param categoryId The ID of the category to which the transaction belongs.
     * @param transactionId The ID of the transaction to be fetched.
     * @return The transaction that matches the specified user ID, category ID, and transaction ID.
     * @throws EtResourceNotFoundException If the transaction is not found.
     */
    Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

    /**
     * Creates a new transaction for a given user and category.
     *
     * @param userId The ID of the user for whom the transaction is to be created.
     * @param categoryId The ID of the category to which the transaction belongs.
     * @param amount The amount of the transaction.
     * @param note The note for the transaction.
     * @param transactionDate The date of the transaction.
     * @return The ID of the newly created transaction.
     * @throws EtBadRequestException If the request to create the transaction is invalid.
     */
    Integer create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException;

    /**
     * Updates an existing transaction for a given user and category.
     *
     * @param userId The ID of the user to whom the transaction belongs.
     * @param categoryId The ID of the category to which the transaction belongs.
     * @param transactionId The ID of the transaction to be updated.
     * @param transaction The transaction object containing the updated details.
     * @throws EtBadRequestException If the request to update the transaction is invalid.
     */
    void update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;

    /**
     * Removes a transaction by user ID, category ID, and transaction ID.
     *
     * @param userId The ID of the user to whom the transaction belongs.
     * @param categoryId The ID of the category to which the transaction belongs.
     * @param transactionId The ID of the transaction to be removed.
     * @throws EtResourceNotFoundException If the transaction is not found.
     */
    void removeById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException;

}
