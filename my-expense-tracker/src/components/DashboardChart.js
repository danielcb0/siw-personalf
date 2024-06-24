import React from 'react';
import { Bar } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend } from 'chart.js';

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend);

const DashboardChart = ({ categories }) => {
    const data = {
        labels: categories.map(category => category.title),
        datasets: [
            {
                label: 'Total Amount',
                data: categories.map(category => category.totalExpense),
                backgroundColor: categories.map(() => '#' + Math.floor(Math.random() * 16777215).toString(16)),
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
                text: 'Total Transactions by Category',
            },
        },
    };

    return <Bar data={data} options={options} />;
};

export default DashboardChart;
