package it.uniroma3.expensetracker.services;

import it.uniroma3.expensetracker.domain.Category;
import it.uniroma3.expensetracker.exceptions.EtBadRequestException;
import it.uniroma3.expensetracker.exceptions.EtResourceNotFoundException;
import it.uniroma3.expensetracker.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the CategoryService interface.
 * This class handles the business logic related to categories and delegates database operations to the CategoryRepository.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Fetches all categories for a specific user.
     *
     * @param userId The ID of the user whose categories are to be fetched.
     * @return A list of categories.
     */
    @Override
    public List<Category> fetchAllCategories(Integer userId) {
        return categoryRepository.findAll(userId);
    }

    /**
     * Fetches a specific category by its ID for a specific user.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category to be fetched.
     * @return The category with the specified ID.
     * @throws EtResourceNotFoundException If the category is not found.
     */
    @Override
    public Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        return categoryRepository.findById(userId, categoryId);
    }

    /**
     * Adds a new category for a specific user.
     *
     * @param userId The ID of the user.
     * @param title The title of the category.
     * @param description The description of the category.
     * @return The newly added category.
     * @throws EtBadRequestException If there is a problem with the request.
     */
    @Override
    public Category addCategory(Integer userId, String title, String description) throws EtBadRequestException {
        int categoryId = categoryRepository.create(userId, title, description);
        return categoryRepository.findById(userId, categoryId);
    }

    /**
     * Updates an existing category for a specific user.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category to be updated.
     * @param category The updated category data.
     * @throws EtBadRequestException If there is a problem with the request.
     */
    @Override
    public void updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException {
        categoryRepository.update(userId, categoryId, category);
    }

    /**
     * Removes a category and all its associated transactions for a specific user.
     *
     * @param userId The ID of the user.
     * @param categoryId The ID of the category to be removed.
     * @throws EtResourceNotFoundException If the category is not found.
     */
    @Override
    public void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        this.fetchCategoryById(userId, categoryId);  // Ensure the category exists before attempting to delete
        categoryRepository.removeById(userId, categoryId);
    }
}
