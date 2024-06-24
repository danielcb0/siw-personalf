package it.uniroma3.expensetracker.services;

import it.uniroma3.expensetracker.domain.Category;
import it.uniroma3.expensetracker.exceptions.EtBadRequestException;
import it.uniroma3.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;

/**
 * Service interface for managing categories.
 */
public interface CategoryService {

    /**
     * Fetches all categories for a specific user.
     *
     * @param userId The ID of the user whose categories are to be fetched.
     * @return A list of categories.
     */
    List<Category> fetchAllCategories(Integer userId);

    /**
     * Fetches a specific category by its ID for a specific user.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category to be fetched.
     * @return The category with the specified ID.
     * @throws EtResourceNotFoundException If the category is not found.
     */
    Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    /**
     * Adds a new category for a specific user.
     *
     * @param userId The ID of the user.
     * @param title The title of the category.
     * @param description The description of the category.
     * @return The newly added category.
     * @throws EtBadRequestException If there is a problem with the request.
     */
    Category addCategory(Integer userId, String title, String description) throws EtBadRequestException;

    /**
     * Updates an existing category for a specific user.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category to be updated.
     * @param category The updated category data.
     * @throws EtBadRequestException If there is a problem with the request.
     */
    void updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;

    /**
     * Removes a category and all its associated transactions for a specific user.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category to be removed.
     * @throws EtResourceNotFoundException If the category is not found.
     */
    void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

}
