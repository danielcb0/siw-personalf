import axios from 'axios';

/**
 * Base URL for the API categories endpoint
 * @constant {string}
 */
const API_URL = 'http://localhost:8080/api/categories';

/**
 * Fetches all categories from the API.
 *
 * This function sends a GET request to the `/categories` endpoint. It returns
 * the response data which typically includes a list of categories.
 *
 * @returns {Promise<Object[]>} The response data from the API.
 * @throws {Error} If the request fails.
 *
 * @example
 * getCategories()
 *     .then(data => console.log(data))
 *     .catch(error => console.error('Failed to fetch categories:', error));
 */
export const getCategories = async () => {
    try {
        const response = await axios.get(API_URL, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        });
        return response.data;
    } catch (error) {
        console.error('Failed to fetch categories:', error);
        throw error;
    }
};

/**
 * Deletes a category from the API.
 *
 * This function sends a DELETE request to the `/categories/{categoryId}` endpoint.
 *
 * @param {number} categoryId - The ID of the category to be deleted.
 * @throws {Error} If the request fails.
 *
 * @example
 * deleteCategory(1)
 *     .then(() => console.log('Category deleted'))
 *     .catch(error => console.error('Failed to delete category:', error));
 */
export const deleteCategory = async (categoryId) => {
    try {
        await axios.delete(`${API_URL}/${categoryId}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        });
    } catch (error) {
        console.error('Failed to delete category:', error);
        throw error;
    }
};

/**
 * Creates a new category in the API.
 *
 * This function sends a POST request to the `/categories` endpoint with the provided
 * title and description. It returns the response data which typically includes the
 * created category.
 *
 * @param {string} title - The title of the category.
 * @param {string} description - The description of the category.
 * @returns {Promise<Object>} The response data from the API.
 * @throws {Error} If the request fails.
 *
 * @example
 * createCategory('New Category', 'This is a new category')
 *     .then(data => console.log(data))
 *     .catch(error => console.error('Failed to create category:', error));
 */
export const createCategory = async (title, description) => {
    try {
        const response = await axios.post(API_URL, {
            title,
            description
        }, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        });
        return response.data;
    } catch (error) {
        console.error('Failed to create category:', error);
        throw error;
    }
};

/**
 * Fetches a single category from the API.
 *
 * This function sends a GET request to the `/categories/{categoryId}` endpoint. It returns
 * the response data which typically includes the category details.
 *
 * @param {number} categoryId - The ID of the category to be fetched.
 * @returns {Promise<Object>} The response data from the API.
 * @throws {Error} If the request fails.
 *
 * @example
 * getCategory(1)
 *     .then(data => console.log(data))
 *     .catch(error => console.error('Failed to fetch category:', error));
 */
export const getCategory = async (categoryId) => {
    try {
        const response = await axios.get(`${API_URL}/${categoryId}`, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        });
        return response.data;
    } catch (error) {
        console.error('Failed to fetch category:', error);
        throw error;
    }
};

/**
 * Updates a category in the API.
 *
 * This function sends a PUT request to the `/categories/{categoryId}` endpoint with the provided
 * title and description.
 *
 * @param {number} categoryId - The ID of the category to be updated.
 * @param {string} title - The new title of the category.
 * @param {string} description - The new description of the category.
 * @throws {Error} If the request fails.
 *
 * @example
 * updateCategory(1, 'Updated Title', 'Updated Description')
 *     .then(() => console.log('Category updated'))
 *     .catch(error => console.error('Failed to update category:', error));
 */
export const updateCategory = async (categoryId, title, description) => {
    try {
        await axios.put(`${API_URL}/${categoryId}`, {
            title,
            description
        }, {
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem('token')}`
            }
        });
    } catch (error) {
        console.error('Failed to update category:', error);
        throw error;
    }
};
