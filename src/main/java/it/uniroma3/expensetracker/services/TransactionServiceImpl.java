package it.uniroma3.expensetracker.services;

import it.uniroma3.expensetracker.domain.Transaction;
import it.uniroma3.expensetracker.exceptions.EtBadRequestException;
import it.uniroma3.expensetracker.exceptions.EtResourceNotFoundException;
import it.uniroma3.expensetracker.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the TransactionService interface.
 * Provides methods to manage transactions including fetching, adding, updating, and deleting transactions.
 */
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    /**
     * Fetches all transactions for a specific user and category.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category whose transactions are to be fetched.
     * @return A list of transactions.
     */
    @Override
    public List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId) {
        return transactionRepository.findAll(userId, categoryId);
    }

    /**
     * Fetches a specific transaction by its ID for a specific user and category.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category.
     * @param transactionId The ID of the transaction to be fetched.
     * @return The transaction with the specified ID.
     * @throws EtResourceNotFoundException If the transaction is not found.
     */
    @Override
    public Transaction fetchTransactionById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        return transactionRepository.findById(userId, categoryId, transactionId);
    }

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
    @Override
    public Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException {
        int transactionId = transactionRepository.create(userId, categoryId, amount, note, transactionDate);
        return transactionRepository.findById(userId, categoryId, transactionId);
    }

    /**
     * Updates an existing transaction for a specific user and category.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category.
     * @param transactionId The ID of the transaction to be updated.
     * @param transaction The updated transaction data.
     * @throws EtBadRequestException If there is a problem with the request.
     */
    @Override
    public void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException {
        transactionRepository.update(userId, categoryId, transactionId, transaction);
    }

    /**
     * Removes a transaction by its ID for a specific user and category.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category.
     * @param transactionId The ID of the transaction to be removed.
     * @throws EtResourceNotFoundException If the transaction is not found.
     */
    @Override
    public void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourceNotFoundException {
        transactionRepository.removeById(userId, categoryId, transactionId);
    }
}
