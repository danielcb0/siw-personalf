package it.uniroma3.expensetracker.resources;

import it.uniroma3.expensetracker.domain.Transaction;
import it.uniroma3.expensetracker.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for handling transaction-related requests within a specific category.
 */
@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionResource {

    @Autowired
    TransactionService transactionService;

    /**
     * Fetches all transactions for a specific category and authenticated user.
     *
     * @param request HttpServletRequest to get the authenticated user's ID.
     * @param categoryId ID of the category whose transactions are to be fetched.
     * @return ResponseEntity containing a list of transactions and an HTTP status code.
     */
    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest request,
                                                                @PathVariable("categoryId") Integer categoryId) {
        int userId = (Integer) request.getAttribute("userId");
        List<Transaction> transactions = transactionService.fetchAllTransactions(userId, categoryId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    /**
     * Fetches a specific transaction by ID within a category for the authenticated user.
     *
     * @param request HttpServletRequest to get the authenticated user's ID.
     * @param categoryId ID of the category.
     * @param transactionId ID of the transaction to fetch.
     * @return ResponseEntity containing the transaction and an HTTP status code.
     */
    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(HttpServletRequest request,
                                                          @PathVariable("categoryId") Integer categoryId,
                                                          @PathVariable("transactionId") Integer transactionId) {
        int userId = (Integer) request.getAttribute("userId");
        Transaction transaction = transactionService.fetchTransactionById(userId,                                                      categoryId, transactionId);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    /**
     * Adds a new transaction to a specific category for the authenticated user.
     *
     * @param request HttpServletRequest to get the authenticated user's ID.
     * @param categoryId ID of the category to add the transaction to.
     * @param transactionMap Map containing the transaction details.
     * @return ResponseEntity containing the created transaction and an HTTP status code.
     */
    @PostMapping("")
    public ResponseEntity<Transaction> addTransaction(HttpServletRequest request,
                                                      @PathVariable("categoryId") Integer categoryId,
                                                      @RequestBody Map<String, Object> transactionMap) {
        int userId = (Integer) request.getAttribute("userId");
        Double amount = Double.valueOf(transactionMap.get("amount").toString());
        String note = (String) transactionMap.get("note");
        Long transactionDate = Long.valueOf(transactionMap.get("transactionDate").toString());
        Transaction transaction = transactionService.addTransaction(userId, categoryId, amount, note, transactionDate);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    /**
     * Updates an existing transaction within a specific category for the authenticated user.
     *
     * @param request HttpServletRequest to get the authenticated user's ID.
     * @param categoryId ID of the category.
     * @param transactionId ID of the transaction to update.
     * @param transaction Transaction object containing updated data.
     * @return ResponseEntity containing a success flag and an HTTP status code.
     */
    @PutMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> updateTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer transactionId,
                                                                  @RequestBody Transaction transaction) {
        int userId = (Integer) request.getAttribute("userId");
        transactionService.updateTransaction(userId, categoryId, transactionId, transaction);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * Deletes a transaction within a specific category for the authenticated user.
     *
     * @param request HttpServletRequest to get the authenticated user's ID.
     * @param categoryId ID of the category.
     * @param transactionId ID of the transaction to delete.
     * @return ResponseEntity containing a success flag and an HTTP status code.
     */
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Map<String, Boolean>> deleteTransaction(HttpServletRequest request,
                                                                  @PathVariable("categoryId") Integer categoryId,
                                                                  @PathVariable("transactionId") Integer transactionId) {
        int userId = (Integer) request.getAttribute("userId");
        transactionService.removeTransaction(userId, categoryId, transactionId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}

