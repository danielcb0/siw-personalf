package it.uniroma3.expensetracker.domain;

/**
 * The Transaction class represents a financial transaction in the Expense Tracker application.
 * Each transaction is associated with a specific category and user, and contains details about the amount, note, and transaction date.
 */
public class Transaction {

    // Fields representing the properties of the transaction
    private Integer transactionId;  // Unique identifier for the transaction
    private Integer categoryId;     // Identifier of the category to which this transaction belongs
    private Integer userId;         // Identifier of the user who made this transaction
    private Double amount;          // Amount of money involved in the transaction
    private String note;            // Note or description about the transaction
    private Long transactionDate;   // Date of the transaction represented in milliseconds since epoch

    /**
     * Constructs a new Transaction with the specified details.
     *
     * @param transactionId   the unique identifier for the transaction
     * @param categoryId      the identifier of the category to which this transaction belongs
     * @param userId          the identifier of the user who made this transaction
     * @param amount          the amount of money involved in the transaction
     * @param note            the note or description about the transaction
     * @param transactionDate the date of the transaction in milliseconds since epoch
     */
    public Transaction(Integer transactionId, Integer categoryId, Integer userId, Double amount, String note, Long transactionDate) {
        this.transactionId = transactionId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.amount = amount;
        this.note = note;
        this.transactionDate = transactionDate;
    }

    // Getter and setter methods for each field

    /**
     * Gets the unique identifier for the transaction.
     *
     * @return the transactionId
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the unique identifier for the transaction.
     *
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Gets the identifier of the category to which this transaction belongs.
     *
     * @return the categoryId
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the identifier of the category to which this transaction belongs.
     *
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the identifier of the user who made this transaction.
     *
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the identifier of the user who made this transaction.
     *
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets the amount of money involved in the transaction.
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of money involved in the transaction.
     *
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets the note or description about the transaction.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the note or description about the transaction.
     *
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets the date of the transaction represented in milliseconds since epoch.
     *
     * @return the transactionDate
     */
    public Long getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the date of the transaction represented in milliseconds since epoch.
     *
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }
}
