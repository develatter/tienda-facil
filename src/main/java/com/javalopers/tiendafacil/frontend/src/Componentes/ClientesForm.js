import React, { useState } from 'react';
import { TextField, Button } from '@mui/material';
import { saveClient } from '../services/ClienteService';  // Asegúrate de que esto esté correctamente definido

const ClienteForm = () => {
  const [customerData, setCustomerData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    telefono: '',
    address: '',
  });

  const handleChange = (e) => {
    setCustomerData({
      ...customerData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await saveClient(customerData);  // Asegúrate de que esta función esté correctamente definida
      console.log('Cliente guardado:', response);
    } catch (error) {
      console.error('Error al guardar cliente:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <TextField
        label="Nombre"
        name="firstName"
        value={customerData.firstName}
        onChange={handleChange}
        fullWidth
        required
      />
      <TextField
        label="Apellido"
        name="lastName"
        value={customerData.lastName}
        onChange={handleChange}
        fullWidth
        required
      />
      <TextField
        label="Correo Electrónico"
        name="email"
        value={customerData.email}
        onChange={handleChange}
        fullWidth
        required
      />
      <TextField
        label="Teléfono"
        name="telefono"
        value={customerData.telefono}
        onChange={handleChange}
        fullWidth
      />
      <TextField
        label="Dirección"
        name="address"
        value={customerData.address}
        onChange={handleChange}
        fullWidth
      />
      <Button type="submit" variant="contained" color="primary">
        Guardar Cliente
      </Button>
    </form>
  );
};

export default ClienteForm;





















