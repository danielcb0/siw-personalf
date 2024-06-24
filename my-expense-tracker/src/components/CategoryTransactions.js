import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getTransactions, createTransaction, deleteTransaction, updateTransaction } from '../services/transactionService';
import CategoryChart from './CategoryChart'; // Importa el nuevo componente

const CategoryTransactions = () => {
    const { categoryId } = useParams();
    const [transactions, setTransactions] = useState([]);
    const [newTransaction, setNewTransaction] = useState({ amount: '', note: '', transactionDate: '' });
    const [editMode, setEditMode] = useState(false);
    const [currentTransaction, setCurrentTransaction] = useState(null);

    useEffect(() => {
        const fetchTransactions = async () => {
            try {
                const data = await getTransactions(categoryId);
                setTransactions(data);
            } catch (error) {
                console.error(error);
            }
        };

        fetchTransactions();
    }, [categoryId]);

    const handleChange = (e) => {
        setNewTransaction({
            ...newTransaction,
            [e.target.name]: e.target.value,
        });
    };

    const handleAddTransaction = async () => {
        try {
            await createTransaction(categoryId, newTransaction);
            const data = await getTransactions(categoryId);
            setTransactions(data);
            setNewTransaction({ amount: '', note: '', transactionDate: '' });
        } catch (error) {
            console.error('Failed to add transaction', error);
        }
    };

    const handleDeleteTransaction = async (transactionId) => {
        try {
            await deleteTransaction(categoryId, transactionId);
            const data = await getTransactions(categoryId);
            setTransactions(data);
        } catch (error) {
            console.error('Failed to delete transaction', error);
        }
    };

    const handleEditTransaction = (transaction) => {
        setCurrentTransaction(transaction);
        setNewTransaction({
            amount: transaction.amount,
            note: transaction.note,
            transactionDate: new Date(transaction.transactionDate).toISOString().split('T')[0]
        });
        setEditMode(true);
    };

    const handleUpdateTransaction = async () => {
        try {
            await updateTransaction(categoryId, currentTransaction.transactionId, newTransaction);
            const data = await getTransactions(categoryId);
            setTransactions(data);
            setNewTransaction({ amount: '', note: '', transactionDate: '' });
            setEditMode(false);
            setCurrentTransaction(null);
        } catch (error) {
            console.error('Failed to update transaction', error);
        }
    };

    return (
        <div>
            <h2>Transactions for Category {categoryId}</h2>
            <CategoryChart transactions={transactions} /> {/* Añade el componente de gráfico */}
            <ul>
                {transactions.map(transaction => (
                    <li key={transaction.transactionId}>
                        Amount: {transaction.amount}, Note: {transaction.note}, Date: {new Date(transaction.transactionDate).toLocaleDateString()}
                        <button onClick={() => handleEditTransaction(transaction)}>Edit</button>
                        <button onClick={() => handleDeleteTransaction(transaction.transactionId)}>Delete</button>
                    </li>
                ))}
            </ul>
            <h3>{editMode ? 'Edit Transaction' : 'Add Transaction'}</h3>
            <input
                type="number"
                name="amount"
                value={newTransaction.amount}
                onChange={handleChange}
                placeholder="Amount"
                required
            />
            <input
                type="text"
                name="note"
                value={newTransaction.note}
                onChange={handleChange}
                placeholder="Note"
                required
            />
            <input
                type="date"
                name="transactionDate"
                value={newTransaction.transactionDate}
                onChange={handleChange}
                required
            />
            <button onClick={editMode ? handleUpdateTransaction : handleAddTransaction}>
                {editMode ? 'Update Transaction' : 'Add Transaction'}
            </button>
        </div>
    );
};

export default CategoryTransactions;
