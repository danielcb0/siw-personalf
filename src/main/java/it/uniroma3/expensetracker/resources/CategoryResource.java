package it.uniroma3.expensetracker.resources;

import it.uniroma3.expensetracker.domain.Category;
import it.uniroma3.expensetracker.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for handling category-related requests.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    /**
     * Fetches all categories for the authenticated user.
     *
     * @param request HttpServletRequest to get the authenticated user's ID.
     * @return ResponseEntity containing a list of categories and an HTTP status code.
     */
    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        List<Category> categories = categoryService.fetchAllCategories(userId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * Fetches a specific category by ID for the authenticated user.
     *
     * @param request HttpServletRequest to get the authenticated user's ID.
     * @param categoryId ID of the category to fetch.
     * @return ResponseEntity containing the category and an HTTP status code.
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(HttpServletRequest request,
                                                    @PathVariable("categoryId") Integer categoryId) {
        int userId = (Integer) request.getAttribute("userId");
        Category category = categoryService.fetchCategoryById(userId, categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Adds a new category for the authenticated user.
     *
     * @param request HttpServletRequest to get the authenticated user's ID.
     * @param categoryMap Map containing the title and description of the new category.
     * @return ResponseEntity containing the created category and an HTTP status code.
     */
    @PostMapping("")
    public ResponseEntity<Category> addCategory(HttpServletRequest request,
                                                @RequestBody Map<String, Object> categoryMap) {
        int userId = (Integer) request.getAttribute("userId");
        String title = (String) categoryMap.get("title");
        String description = (String) categoryMap.get("description");
        Category category = categoryService.addCategory(userId, title, description);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    /**
     * Updates an existing category for the authenticated user.
     *
     * @param request HttpServletRequest to get the authenticated user's ID.
     * @param categoryId ID of the category to update.
     * @param category Category object containing updated data.
     * @return ResponseEntity containing a success flag and an HTTP status code.
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> updateCategory(HttpServletRequest request,
                                                               @PathVariable("categoryId") Integer categoryId,
                                                               @RequestBody Category category) {
        int userId = (Integer) request.getAttribute("userId");
        categoryService.updateCategory(userId, categoryId, category);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**
     * Deletes a category and all its transactions for the authenticated user.
     *
     * @param request HttpServletRequest to get the authenticated user's ID.
     * @param categoryId ID of the category to delete.
     * @return ResponseEntity containing a success flag and an HTTP status code.
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(HttpServletRequest request,
                                                               @PathVariable("categoryId") Integer categoryId) {
        int userId = (Integer) request.getAttribute("userId");
        categoryService.removeCategoryWithAllTransactions(userId, categoryId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
