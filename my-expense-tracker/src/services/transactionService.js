import axios from 'axios';

/**
 * Base URL for the API
 * @constant {string}
 */
const API_URL = 'http://localhost:8080/api';

/**
 * Fetches all transactions for a specific category from the API.
 *
 * This function sends a GET request to the `/categories/{categoryId}/transactions` endpoint.
 * It returns the response data which typically includes a list of transactions.
 *
 * @param {number} categoryId - The ID of the category to fetch transactions for.
 * @returns {Promise<Object[]>} The response data from the API.
 * @throws {Error} If the token is not found or the request fails.
 *
 * @example
 * getTransactions(1)
 *     .then(data => console.log(data))
 *     .catch(error => console.error('Failed to fetch transactions:', error));
 */
export const getTransactions = async (categoryId) => {
    const token = localStorage.getItem('token');
    if (!token) throw new Error('No token found');

    const response = await axios.get(`${API_URL}/categories/${categoryId}/transactions`, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
    return response.data;
};

/**
 * Creates a new transaction in a specific category in the API.
 *
 * This function sends a POST request to the `/categories/{categoryId}/transactions` endpoint
 * with the provided transaction data. It returns the response data which typically includes
 * the created transaction.
 *
 * @param {number} categoryId - The ID of the category to create the transaction in.
 * @param {Object} transaction - The transaction data to be created.
 * @param {number} transaction.amount - The amount of the transaction.
 * @param {string} transaction.note - The note for the transaction.
 * @param {string} transaction.transactionDate - The date of the transaction in YYYY-MM-DD format.
 * @returns {Promise<Object>} The response data from the API.
 * @throws {Error} If the token is not found or the request fails.
 *
 * @example
 * createTransaction(1, { amount: 100, note: 'Grocery', transactionDate: '2023-01-01' })
 *     .then(data => console.log(data))
 *     .catch(error => console.error('Failed to create transaction:', error));
 */
export const createTransaction = async (categoryId, transaction) => {
    const token = localStorage.getItem('token');
    if (!token) throw new Error('No token found');

    const response = await axios.post(`${API_URL}/categories/${categoryId}/transactions`, {
        amount: parseFloat(transaction.amount),
        note: transaction.note,
        transactionDate: new Date(transaction.transactionDate).getTime()
    }, {
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
        }
    });
    return response.data;
};

/**
 * Deletes a specific transaction from a category in the API.
 *
 * This function sends a DELETE request to the `/categories/{categoryId}/transactions/{transactionId}` endpoint.
 *
 * @param {number} categoryId - The ID of the category that contains the transaction.
 * @param {number} transactionId - The ID of the transaction to be deleted.
 * @returns {Promise<Object>} The response data from the API.
 * @throws {Error} If the token is not found or the request fails.
 *
 * @example
 * deleteTransaction(1, 100)
 *     .then(data => console.log(data))
 *     .catch(error => console.error('Failed to delete transaction:', error));
 */
export const deleteTransaction = async (categoryId, transactionId) => {
    const token = localStorage.getItem('token');
    if (!token) throw new Error('No token found');

    const response = await axios.delete(`${API_URL}/categories/${categoryId}/transactions/${transactionId}`, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    });
    return response.data;
};

/**
 * Updates a specific transaction in a category in the API.
 *
 * This function sends a PUT request to the `/categories/{categoryId}/transactions/{transactionId}` endpoint
 * with the provided updated transaction data.
 *
 * @param {number} categoryId - The ID of the category that contains the transaction.
 * @param {number} transactionId - The ID of the transaction to be updated.
 * @param {Object} transaction - The updated transaction data.
 * @param {number} transaction.amount - The updated amount of the transaction.
 * @param {string} transaction.note - The updated note for the transaction.
 * @param {string} transaction.transactionDate - The updated date of the transaction in YYYY-MM-DD format.
 * @returns {Promise<Object>} The response data from the API.
 * @throws {Error} If the token is not found or the request fails.
 *
 * @example
 * updateTransaction(1, 100, { amount: 150, note: 'Updated Note', transactionDate: '2023-01-02' })
 *     .then(data => console.log(data))
 *     .catch(error => console.error('Failed to update transaction:', error));
 */
export const updateTransaction = async (categoryId, transactionId, transaction) => {
    const token = localStorage.getItem('token');
    if (!token) throw new Error('No token found');

    const response = await axios.put(`${API_URL}/categories/${categoryId}/transactions/${transactionId}`, {
        amount: parseFloat(transaction.amount),
        note: transaction.note,
        transactionDate: new Date(transaction.transactionDate).getTime()
    }, {
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`
        }
    });
    return response.data;
};
