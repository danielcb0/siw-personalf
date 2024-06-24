/**
 * Generates an authorization header for API requests.
 *
 * This function retrieves the user object from the browser's localStorage,
 * parses it to JSON, and checks if it contains a valid JWT token. If a token is
 * found, it returns an object containing the Authorization header with the Bearer token.
 * If no token is found, it returns an empty object.
 *
 * @returns {Object} An object containing the Authorization header if the user is logged in,
 *                   otherwise an empty object.
 */
export default function authHeader() {
    // Retrieve the user object from localStorage and parse it to JSON
    const user = JSON.parse(localStorage.getItem('user'));

    // Check if the user object exists and has a token
    if (user && user.token) {
        // Return an object with the Authorization header containing the Bearer token
        return { Authorization: 'Bearer ' + user.token };
    } else {
        // Return an empty object if no user or token is found
        return {};
    }
}
