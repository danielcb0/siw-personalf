package it.uniroma3.expensetracker.repositories;

import it.uniroma3.expensetracker.domain.Category;
import it.uniroma3.expensetracker.exceptions.EtBadRequestException;
import it.uniroma3.expensetracker.exceptions.EtResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * Implementation of the CategoryRepository interface.
 * This class provides CRUD operations for the Category entity.
 */
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    // SQL queries for the Category entity operations
    private static final String SQL_FIND_ALL = "SELECT C.CATEGORY_ID, C.USER_ID, C.TITLE, C.DESCRIPTION, " +
            "COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE " +
            "FROM ET_TRANSACTIONS T RIGHT OUTER JOIN ET_CATEGORIES C ON C.CATEGORY_ID = T.CATEGORY_ID " +
            "WHERE C.USER_ID = ? GROUP BY C.CATEGORY_ID";
    private static final String SQL_FIND_BY_ID = "SELECT C.CATEGORY_ID, C.USER_ID, C.TITLE, C.DESCRIPTION, " +
            "COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE " +
            "FROM ET_TRANSACTIONS T RIGHT OUTER JOIN ET_CATEGORIES C ON C.CATEGORY_ID = T.CATEGORY_ID " +
            "WHERE C.USER_ID = ? AND C.CATEGORY_ID = ? GROUP BY C.CATEGORY_ID";
    private static final String SQL_CREATE = "INSERT INTO ET_CATEGORIES (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION) VALUES(NEXTVAL('ET_CATEGORIES_SEQ'), ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE ET_CATEGORIES SET TITLE = ?, DESCRIPTION = ? " +
            "WHERE USER_ID = ? AND CATEGORY_ID = ?";
    private static final String SQL_DELETE_CATEGORY = "DELETE FROM ET_CATEGORIES WHERE USER_ID = ? AND CATEGORY_ID = ?";
    private static final String SQL_DELETE_ALL_TRANSACTIONS = "DELETE FROM ET_TRANSACTIONS WHERE CATEGORY_ID = ?";

    // JdbcTemplate for interacting with the database
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Fetches all categories for a given user.
     *
     * @param userId The ID of the user whose categories are to be fetched.
     * @return A list of categories belonging to the specified user.
     * @throws EtResourceNotFoundException If no categories are found for the user.
     */
    @Override
    public List<Category> findAll(Integer userId) throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId}, categoryRowMapper);
    }

    /**
     * Fetches a specific category by user ID and category ID.
     *
     * @param userId The ID of the user to whom the category belongs.
     * @param categoryId The ID of the category to be fetched.
     * @return The category that matches the specified user ID and category ID.
     * @throws EtResourceNotFoundException If the category is not found.
     */
    @Override
    public Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, categoryId}, categoryRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Category not found");
        }
    }

    /**
     * Creates a new category for a given user.
     *
     * @param userId The ID of the user for whom the category is to be created.
     * @param title The title of the new category.
     * @param description The description of the new category.
     * @return The ID of the newly created category.
     * @throws EtBadRequestException If the request to create the category is invalid.
     */
    @Override
    public Integer create(Integer userId, String title, String description) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setString(2, title);
                ps.setString(3, description);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("CATEGORY_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    /**
     * Updates an existing category for a given user.
     *
     * @param userId The ID of the user to whom the category belongs.
     * @param categoryId The ID of the category to be updated.
     * @param category The category object containing the updated details.
     * @throws EtBadRequestException If the request to update the category is invalid.
     */
    @Override
    public void update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{category.getTitle(), category.getDescription(), userId, categoryId});
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");
        }
    }

    /**
     * Removes a category by user ID and category ID.
     *
     * @param userId The ID of the user to whom the category belongs.
     * @param categoryId The ID of the category to be removed.
     */
    @Override
    public void removeById(Integer userId, Integer categoryId) {
        this.removeAllCatTransactions(categoryId);
        jdbcTemplate.update(SQL_DELETE_CATEGORY, new Object[]{userId, categoryId});
    }

    /**
     * Removes all transactions associated with a given category.
     *
     * @param categoryId The ID of the category whose transactions are to be removed.
     */
    private void removeAllCatTransactions(Integer categoryId) {
        jdbcTemplate.update(SQL_DELETE_ALL_TRANSACTIONS, new Object[]{categoryId});
    }

    // RowMapper for mapping result set rows to Category objects
    private RowMapper<Category> categoryRowMapper = ((rs, rowNum) -> {
        return new Category(rs.getInt("CATEGORY_ID"),
                rs.getInt("USER_ID"),
                rs.getString("TITLE"),
                rs.getString("DESCRIPTION"),
                rs.getDouble("TOTAL_EXPENSE"));
    });
}
