// Importing necessary libraries and functions
import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getCategory, updateCategory } from '../services/categoryService';

// EditCategory component
const EditCategory = () => {
    // Extracting categoryId from the route parameters
    const { categoryId } = useParams();
    // State to store the title of the category
    const [title, setTitle] = useState('');
    // State to store the description of the category
    const [description, setDescription] = useState('');
    // Hook to navigate programmatically
    const navigate = useNavigate();

    // useEffect hook to fetch category data when the component mounts
    useEffect(() => {
        fetchCategory();
    }, []);

    // Async function to fetch the category data from the API
    const fetchCategory = async () => {
        try {
            // Calling getCategory function to fetch the category data
            const category = await getCategory(categoryId);
            // Updating the title and description states with the fetched data
            setTitle(category.title);
            setDescription(category.description);
        } catch (error) {
            // Logging any errors that occur during the fetch
            console.error('Failed to fetch category', error);
        }
    };

    // Function to handle the update of the category
    const handleUpdate = async () => {
        try {
            // Calling updateCategory function to update the category data
            await updateCategory(categoryId, title, description);
            // Navigating back to the dashboard after updating
            navigate('/dashboard');
        } catch (error) {
            // Logging any errors that occur during the update
            console.error('Failed to update category', error);
        }
    };

    return (
        <div>
            {/* Title for the edit category page */}
            <h1>Edit Category</h1>
            {/* Input field for the category title */}
            <input
                type="text"
                placeholder="Title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
            />
            {/* Input field for the category description */}
            <input
                type="text"
                placeholder="Description"
                value={description}
                onChange={(e) => setDescription(e.target.value)}
            />
            {/* Button to trigger the handleUpdate function */}
            <button onClick={handleUpdate}>Update Category</button>
        </div>
    );
};

// Exporting the EditCategory component as the default export
export default EditCategory;
