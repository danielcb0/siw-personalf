// Import necessary libraries and functions
import React, { useState, useEffect } from 'react';
import { createTransaction, getTransactions, deleteTransaction, updateTransaction } from '../services/transactionService';
import { useParams } from 'react-router-dom';

// TransactionList component
const TransactionList = () => {
    // Extract categoryId from URL parameters
    const { categoryId } = useParams();
    // State to store the list of transactions
    const [transactions, setTransactions] = useState([]);
    // State to store the data of a new transaction
    const [newTransaction, setNewTransaction] = useState({ amount: '', note: '', transactionDate: '' });
    // State to toggle between add and edit mode
    const [editMode, setEditMode] = useState(false);
    // State to store the current transaction being edited
    const [currentTransaction, setCurrentTransaction] = useState(null);

    // Fetch transactions when the component mounts or categoryId changes
    useEffect(() => {
        const fetchTransactions = async () => {
            try {
                // Fetch transactions from the API
                const data = await getTransactions(categoryId);
                // Update the state with the fetched data
                setTransactions(data);
            } catch (error) {
                console.error(error);
            }
        };

        fetchTransactions();
    }, [categoryId]);

    // Handle input change for transaction form
    const handleChange = (e) => {
        setNewTransaction({
            ...newTransaction,
            [e.target.name]: e.target.value,
        });
    };

    // Handle adding a new transaction
    const handleAddTransaction = async () => {
        try {
            // Call the API to create a new transaction
            await createTransaction(categoryId, newTransaction);
            // Fetch the updated list of transactions
            const data = await getTransactions(categoryId);
            // Update the state with the new list of transactions
            setTransactions(data);
            // Reset the new transaction state
            setNewTransaction({ amount: '', note: '', transactionDate: '' });
        } catch (error) {
            console.error('Failed to add transaction', error);
        }
    };

    // Handle deleting a transaction
    const handleDeleteTransaction = async (transactionId) => {
        try {
            // Call the API to delete the transaction
            await deleteTransaction(categoryId, transactionId);
            // Fetch the updated list of transactions
            const data = await getTransactions(categoryId);
            // Update the state with the new list of transactions
            setTransactions(data);
        } catch (error) {
            console.error('Failed to delete transaction', error);
        }
    };

    // Handle selecting a transaction to edit
    const handleEditTransaction = (transaction) => {
        // Set the current transaction being edited
        setCurrentTransaction(transaction);
        // Populate the form with the transaction data
        setNewTransaction({
            amount: transaction.amount,
            note: transaction.note,
            transactionDate: new Date(transaction.transactionDate).toISOString().split('T')[0]
        });
        // Set the edit mode to true
        setEditMode(true);
    };

    // Handle updating a transaction
    const handleUpdateTransaction = async () => {
        try {
            // Call the API to update the transaction
            await updateTransaction(categoryId, currentTransaction.transactionId, newTransaction);
            // Fetch the updated list of transactions
            const data = await getTransactions(categoryId);
            // Update the state with the new list of transactions
            setTransactions(data);
            // Reset the new transaction state
            setNewTransaction({ amount: '', note: '', transactionDate: '' });
            // Reset the edit mode and current transaction
            setEditMode(false);
            setCurrentTransaction(null);
        } catch (error) {
            console.error('Failed to update transaction', error);
        }
    };

    return (
        <div>
            <h2>Transactions</h2>
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

// Exporting the TransactionList component as the default export
export default TransactionList;
