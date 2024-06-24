import React from 'react';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from 'chart.js';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

const CategoryChart = ({ transactions }) => {
    const data = {
        labels: transactions.map(transaction => transaction.note),
        datasets: [
            {
                label: 'Amount',
                data: transactions.map(transaction => transaction.amount),
                backgroundColor: transactions.map(() => '#' + Math.floor(Math.random() * 16777215).toString(16)),
            },
        ],
    };

    const options = {
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Transactions Chart',
            },
        },
    };

    return <Bar data={data} options={options} />;
};

export default CategoryChart;
