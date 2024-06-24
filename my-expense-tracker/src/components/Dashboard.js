import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { getCategories, deleteCategory, createCategory } from '../services/categoryService';
import DashboardChart from './DashboardChart'; // Importa el nuevo componente

const Dashboard = () => {
    const [categories, setCategories] = useState([]);
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetchCategories();
    }, []);

    const fetchCategories = async () => {
        try {
            const data = await getCategories();
            setCategories(data);
        } catch (error) {
            console.error('Failed to fetch categories', error);
        }
    };

    const handleDelete = async (categoryId) => {
        try {
            await deleteCategory(categoryId);
            fetchCategories(); // Refresh categories after deletion
        } catch (error) {
            console.error('Failed to delete category', error);
        }
    };

    const handleAddCategory = async () => {
        try {
            await createCategory(title, description);
            setTitle('');
            setDescription('');
            fetchCategories(); // Refresh categories after adding
        } catch (error) {
            console.error('Failed to add category', error);
        }
    };

    return (
        <div>
            <h1>Dashboard</h1>
            <DashboardChart categories={categories} /> {/* Añade el componente de gráfico */}
            <div className="categories">
                <h2>Categories</h2>
                <ul>
                    {categories.map(category => (
                        <li key={category.categoryId}>
                            <Link to={`/categories/${category.categoryId}`}>
                                {category.title}
                            </Link>
                            <button onClick={() => handleDelete(category.categoryId)}>Delete</button>
                            <button onClick={() => navigate(`/edit-category/${category.categoryId}`)}>Edit</button>
                        </li>
                    ))}
                </ul>
                <h3>Add Category</h3>
                <input
                    type="text"
                    placeholder="Title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                />
                <button onClick={handleAddCategory}>Add Category</button>
            </div>
        </div>
    );
};

export default Dashboard;
