package it.uniroma3.expensetracker.domain;

/**
 * The Category class represents an expense category in the Expense Tracker application.
 * Each category belongs to a specific user and contains a title, description, and the total expense associated with it.
 */
public class Category {

    // Fields representing the properties of the category
    private Integer categoryId;    // Unique identifier for the category
    private Integer userId;        // Identifier of the user to whom this category belongs
    private String title;          // Title of the category
    private String description;    // Description of the category
    private Double totalExpense;   // Total expense associated with this category

    /**
     * Constructs a new Category with the specified details.
     *
     * @param categoryId   the unique identifier for the category
     * @param userId       the identifier of the user to whom this category belongs
     * @param title        the title of the category
     * @param description  the description of the category
     * @param totalExpense the total expense associated with this category
     */
    public Category(Integer categoryId, Integer userId, String title, String description, Double totalExpense) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.totalExpense = totalExpense;
    }

    // Getter and setter methods for each field

    /**
     * Gets the unique identifier for the category.
     *
     * @return the categoryId
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the unique identifier for the category.
     *
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the identifier of the user to whom this category belongs.
     *
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the identifier of the user to whom this category belongs.
     *
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets the title of the category.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the category.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the category.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the category.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the total expense associated with this category.
     *
     * @return the totalExpense
     */
    public Double getTotalExpense() {
        return totalExpense;
    }

    /**
     * Sets the total expense associated with this category.
     *
     * @param totalExpense the totalExpense to set
     */
    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }
}
