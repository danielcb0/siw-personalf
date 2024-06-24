// Importing necessary libraries and functions
import React, { useEffect, useState } from 'react';
import { getCategories } from '../services/categoryService'; // Importing the getCategories function from the categoryService

// CategoryList component
function CategoryList() {
    // State to store the list of categories
    const [categories, setCategories] = useState([]);

    // useEffect hook to fetch categories when the component mounts
    useEffect(() => {
        // Async function to fetch categories
        const fetchCategories = async () => {
            try {
                // Calling getCategories function to fetch category data
                const categoriesData = await getCategories();
                // Updating the categories state with the fetched data
                setCategories(categoriesData);
            } catch (error) {
                // Logging any errors that occur during the fetch
                console.error('Error fetching categories:', error);
            }
        };

        // Calling the fetchCategories function
        fetchCategories();
    }, []); // Empty dependency array ensures this effect runs only once after the initial render

    return (
        <div>
            {/* Title for the category list */}
            <h1>Categories</h1>
            {/* Mapping over the categories state to render each category */}
            {categories.map(category => (
                // Each category should have a unique key prop
                <div key={category.categoryId}>
                    {/* Displaying the title of the category */}
                    <h2>{category.title}</h2>
                    {/* Displaying the description of the category */}
                    <p>{category.description}</p>
                </div>
            ))}
        </div>
    );
}

// Exporting the CategoryList component as the default export
export default CategoryList;
