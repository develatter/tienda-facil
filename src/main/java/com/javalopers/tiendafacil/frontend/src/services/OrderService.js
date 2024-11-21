// src/services/OrderService.js
import axios from 'axios';

const API_URL = 'http://localhost:9000/api/orders';

// Función para crear un pedido
export const createOrder = async (orderData) => {
  try {
    const response = await axios.post(`${API_URL}`, orderData);
    return response.data;
  } catch (error) {
    console.error('Error al crear el pedido:', error);
    throw error;
  }
};

// Función para obtener todos los pedidos
export const getAllOrders = async () => {
  try {
    const response = await axios.get(`${API_URL}`);
    return response.data;
  } catch (error) {
    console.error('Error al obtener los pedidos:', error);
    throw error;
  }
};








