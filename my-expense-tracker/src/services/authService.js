import axios from 'axios';

/**
 * Base URL for the API
 * @constant {string}
 */
const API_URL = 'http://localhost:8080/api';

/**
 * Sends a login request to the API.
 *
 * This function sends a POST request to the `/users/login` endpoint with the provided
 * email and password. It returns the response data which typically includes the user
 * details and a JWT token.
 *
 * @param {string} email - The email address of the user.
 * @param {string} password - The password of the user.
 * @returns {Promise<Object>} The response data from the API.
 * @throws {Error} If the request fails.
 *
 * @example
 * login('user@example.com', 'password123')
 *     .then(data => console.log(data))
 *     .catch(error => console.error('Login failed:', error));
 */
export const login = async (email, password) => {
    try {
        const response = await axios.post(`${API_URL}/users/login`, { email, password });
        return response.data;
    } catch (error) {
        console.error('Login failed:', error);
        throw error;
    }
};

/**
 * Sends a registration request to the API.
 *
 * This function sends a POST request to the `/users/register` endpoint with the provided
 * user details. It returns the response data which typically includes the user details
 * and a JWT token.
 *
 * @param {string} firstName - The first name of the user.
 * @param {string} lastName - The last name of the user.
 * @param {string} email - The email address of the user.
 * @param {string} password - The password of the user.
 * @returns {Promise<Object>} The response data from the API.
 * @throws {Error} If the request fails.
 *
 * @example
 * register('John', 'Doe', 'john.doe@example.com', 'password123')
 *     .then(data => console.log(data))
 *     .catch(error => console.error('Registration failed:', error));
 */
export const register = async (firstName, lastName, email, password) => {
    try {
        const response = await axios.post(`${API_URL}/users/register`, { firstName, lastName, email, password });
        return response.data;
    } catch (error) {
        console.error('Registration failed:', error);
        throw error;
    }
};
