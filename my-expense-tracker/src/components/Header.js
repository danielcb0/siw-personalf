// Importing necessary libraries and functions
import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

// Header component
const Header = ({ isLoggedIn, handleLogout }) => {
    // Hook to navigate programmatically
    const navigate = useNavigate();

    // Function to handle the logout button click
    const handleLogoutClick = () => {
        // Call the handleLogout function passed as a prop
        handleLogout();
        // Navigate to the login page after logout
        navigate('/login');
    };

    return (
        <header>
            {/* Title of the application */}
            <div className="header-title">Expense Tracker</div>
            {/* Navigation menu */}
            <nav>
                <ul>
                    {/* Link to the dashboard */}
                    <li>
                        <Link to="/dashboard">Dashboard</Link>
                    </li>
                    {/* Conditional rendering based on login status */}
                    {!isLoggedIn && (
                        <>
                            {/* Link to the login page */}
                            <li>
                                <Link to="/login">Login</Link>
                            </li>
                            {/* Link to the register page */}
                            <li>
                                <Link to="/register">Register</Link>
                            </li>
                        </>
                    )}
                    {/* Render logout button if the user is logged in */}
                    {isLoggedIn && (
                        <li>
                            <button onClick={handleLogoutClick}>Log Out</button>
                        </li>
                    )}
                </ul>
            </nav>
        </header>
    );
};

// Exporting the Header component as the default export
export default Header;
