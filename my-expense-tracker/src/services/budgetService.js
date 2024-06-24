import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export const getBudget = async () => {
    const token = localStorage.getItem('token');
    const response = await axios.get(`${API_URL}/budget`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
    return response.data;
};

export const updateBudget = async (totalBudget) => {
    const token = localStorage.getItem('token');
    const response = await axios.put(`${API_URL}/budget`, { totalBudget }, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
    return response.data;
};
