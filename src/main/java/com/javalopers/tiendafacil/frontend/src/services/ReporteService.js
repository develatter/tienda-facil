// src/services/reportService.js
import axios from 'axios';

const API_URL = 'http://localhost:9000/api/reports';

const getReports = async () => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('Error al obtener los reportes:', error);
    throw error;
  }
};

export { getReports };
