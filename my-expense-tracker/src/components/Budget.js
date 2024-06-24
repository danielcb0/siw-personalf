import React, { useState, useEffect } from 'react';
import { getBudget, updateBudget } from '../services/budgetService';

const Budget = () => {
    const [budget, setBudget] = useState(0);

    useEffect(() => {
        const fetchBudget = async () => {
            try {
                const data = await getBudget();
                setBudget(data.totalBudget);
            } catch (error) {
                console.error("Error fetching budget:", error);
            }
        };
        fetchBudget();
    }, []);

    const handleUpdateBudget = async (e) => {
        e.preventDefault();
        try {
            const updatedBudget = await updateBudget(budget);
            setBudget(updatedBudget.totalBudget);
        } catch (error) {
            console.error("Error updating budget:", error);
        }
    };

    return (
        <div>
            <h2>Budget</h2>
            <form onSubmit={handleUpdateBudget}>
                <div>
                    <label>Total Budget: </label>
                    <input
                        type="number"
                        value={budget}
                        onChange={(e) => setBudget(e.target.value)}
                    />
                </div>
                <button type="submit">Update Budget</button>
            </form>
        </div>
    );
};

export default Budget;
