package it.uniroma3.expensetracker.repositories;

import it.uniroma3.expensetracker.domain.Category;
import it.uniroma3.expensetracker.exceptions.EtBadRequestException;
import it.uniroma3.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;

/**
 * This interface defines the contract for CategoryRepository.
 * It provides methods for CRUD operations on categories.
 */
public interface CategoryRepository {

    /**
     * Fetches all categories for a given user.
     *
     * @param userId The ID of the user whose categories are to be fetched.
     * @return A list of categories belonging to the specified user.
     * @throws EtResourceNotFoundException If no categories are found for the user.
     */
    List<Category> findAll(Integer userId) throws EtResourceNotFoundException;

    /**
     * Fetches a specific category by user ID and category ID.
     *
     * @param userId The ID of the user to whom the category belongs.
     * @param categoryId The ID of the category to be fetched.
     * @return The category that matches the specified user ID and category ID.
     * @throws EtResourceNotFoundException If the category is not found.
     */
    Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    /**
     * Creates a new category for a given user.
     *
     * @param userId The ID of the user for whom the category is to be created.
     * @param title The title of the new category.
     * @param description The description of the new category.
     * @return The ID of the newly created category.
     * @throws EtBadRequestException If the request to create the category is invalid.
     */
    Integer create(Integer userId, String title, String description) throws EtBadRequestException;

    /**
     * Updates an existing category for a given user.
     *
     * @param userId The ID of the user to whom the category belongs.
     * @param categoryId The ID of the category to be updated.
     * @param category The category object containing the updated details.
     * @throws EtBadRequestException If the request to update the category is invalid.
     */
    void update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;

    /**
     * Removes a category by user ID and category ID.
     *
     * @param userId The ID of the user to whom the category belongs.
     * @param categoryId The ID of the category to be removed.
     */
    void removeById(Integer userId, Integer categoryId);
}
