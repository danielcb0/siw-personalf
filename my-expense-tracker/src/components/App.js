// Importing necessary libraries and components
import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './Login'; // Importing the Login component
import Register from './Register'; // Importing the Register component
import Dashboard from './Dashboard'; // Importing the Dashboard component
import CategoryTransactions from './CategoryTransactions'; // Importing the CategoryTransactions component
import EditCategory from './EditCategory'; // Importing the EditCategory component
import PrivateRoute from '../utils/PrivateRoute'; // Importing the PrivateRoute component to protect certain routes
import Header from './Header'; // Importing the Header component
import Budget from './Budget'; // Import the new Budget component

import '../styles.css'; // Importing the stylesheet

// Main App component
const App = () => {
    // State to manage user login status
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    // useEffect hook to check for token in local storage when the component mounts
    useEffect(() => {
        const token = localStorage.getItem('token');
        setIsLoggedIn(!!token); // Set isLoggedIn to true if token exists
    }, []);

    // Function to handle user logout
    const handleLogout = () => {
        localStorage.removeItem('token'); // Remove token from local storage
        setIsLoggedIn(false); // Set isLoggedIn to false
    };

    return (
        // Router component to enable client-side routing
        <Router>
            {/* Header component with props for login status and logout handler */}
            <Header isLoggedIn={isLoggedIn} handleLogout={handleLogout} />
            <div className="container">
                {/* Routes component to define the different routes in the application */}
                <Routes>
                    {/* Route for login page */}
                    <Route path="/login" element={<Login />} />
                    {/* Route for registration page */}
                    <Route path="/register" element={<Register />} />
                    {/* Protected route for dashboard, accessible only if the user is logged in */}
                    <Route path="/dashboard" element={
                        <PrivateRoute>
                            <Dashboard />
                        </PrivateRoute>
                    } />
                    {/* Protected route for viewing category transactions, accessible only if the user is logged in */}
                    <Route path="/categories/:categoryId" element={
                        <PrivateRoute>
                            <CategoryTransactions />
                        </PrivateRoute>
                    } />
                    {/* Protected route for editing a category, accessible only if the user is logged in */}
                    <Route path="/edit-category/:categoryId" element={
                        <PrivateRoute>
                            <EditCategory />
                        </PrivateRoute>
                    } />
                    <Route path="/budget" element={
                        <PrivateRoute>
                            <Budget />
                        </PrivateRoute>
                    } />
                </Routes>
            </div>
        </Router>
    );
};

export default App; // Exporting the App component as the default export
