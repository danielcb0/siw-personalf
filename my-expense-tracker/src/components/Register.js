// Importing necessary libraries and functions
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { register } from '../services/authService';

// Register component
const Register = () => {
    // State variables to store user inputs
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    // Hook to navigate programmatically
    const navigate = useNavigate();

    // Function to handle form submission for registration
    const handleRegister = async (event) => {
        event.preventDefault(); // Prevents the default form submission behavior
        try {
            // Calls the register function from the authService
            await register(firstName, lastName, email, password);
            // Displays a success message
            alert('Registration successful');
            // Navigates to the login page upon successful registration
            navigate('/login');
        } catch (error) {
            // Displays an alert message if registration fails
            alert('Registration failed: ' + error.message);
        }
    };

    return (
        // Form element to handle registration
        <form onSubmit={handleRegister}>
            <h2>Register</h2>
            {/* Input field for first name */}
            <input
                type="text"
                placeholder="First Name"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
                required
                autoComplete="given-name"
            />
            {/* Input field for last name */}
            <input
                type="text"
                placeholder="Last Name"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
                required
                autoComplete="family-name"
            />
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
                autoComplete="new-password"
            />
            {/* Submit button for the form */}
            <button type="submit">Register</button>
        </form>
    );
};

// Exporting the Register component as the default export
export default Register;
