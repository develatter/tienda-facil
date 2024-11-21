// src/Order/OrderForm.js
import React, { useState } from 'react';
import { createOrder } from '../services/OrderService';
import { Button, TextField } from '@mui/material';  // IMPORTACIÓN CORRECTA

const OrderForm = () => {
  const [orderData, setOrderData] = useState({
    customerId: '',
    productId: '',
    quantity: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setOrderData({
      ...orderData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await createOrder(orderData); // Usando 'createOrder'
      console.log('Pedido creado:', response);
    } catch (error) {
      console.error('Error al crear el pedido:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <TextField
        label="Customer ID"
        name="customerId"
        value={orderData.customerId}
        onChange={handleChange}
      />
      <TextField
        label="Product ID"
        name="productId"
        value={orderData.productId}
        onChange={handleChange}
      />
      <TextField
        label="Quantity"
        name="quantity"
        value={orderData.quantity}
        onChange={handleChange}
      />
      <Button type="submit" variant="contained" color="primary">
        Crear Pedido
      </Button>
    </form>
  );
};

export default OrderForm;









