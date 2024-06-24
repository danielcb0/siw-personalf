// Importing necessary libraries and functions
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { login } from '../services/authService';

// Login component
const Login = () => {
    // State variables to store email and password
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    // Hook to navigate programmatically
    const navigate = useNavigate();

    // Function to handle form submission for login
    const handleLogin = async (event) => {
        event.preventDefault(); // Prevents the default form submission behavior
        try {
            // Calls the login function from the authService
            const data = await login(email, password);
            // Stores the received token in local storage
            localStorage.setItem('token', data.token);
            // Navigates to the dashboard upon successful login
            navigate('/dashboard');
        } catch (error) {
            // Displays an alert message if login fails
            alert('Login failed: ' + error.message);
        }
    };

    return (
        // Form element to handle login
        <form onSubmit={handleLogin}>
            <h2>Login</h2>
            {/* Input field for email */}
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
                autoComplete="email"
            />
            {/* Input field for password */}
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                autoComplete="current-password"
            />
            {/* Submit button for the form */}
            <button type="submit">Login</button>
        </form>
    );
};

// Exporting the Login component as the default export
export default Login;
