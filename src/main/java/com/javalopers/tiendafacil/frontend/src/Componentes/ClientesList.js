import React, { useState, useEffect } from 'react';
import { getAllClients } from '../services/ClienteService';  // Asegúrate de que esta función esté importada
import { Container, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';

const ClienteList = () => {
  const [clientes, setClientes] = useState([]);

  useEffect(() => {
    const fetchClientes = async () => {
      try {
        const data = await getAllClients();  // Asegúrate de llamar la función correctamente
        console.log('Datos obtenidos:', data); // Verifica que los datos estén correctos
        setClientes(data);
      } catch (error) {
        console.error('Error al obtener los clientes:', error);
      }
    };
    fetchClientes();
  }, []);

  return (
    <Container>
      <h2>Lista de Clientes</h2>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Nombre</TableCell>
              <TableCell>Correo</TableCell>
              <TableCell>Teléfono</TableCell>
              <TableCell>Dirección</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {clientes.map((cliente) => (
              <TableRow key={cliente.customerId}>
                <TableCell>{cliente.customerId}</TableCell>
                <TableCell>{cliente.firstName} {cliente.lastName}</TableCell>
                <TableCell>{cliente.mail}</TableCell>
                <TableCell>{cliente.telefono ? cliente.telefono : 'No disponible'}</TableCell>
                <TableCell>{cliente.address}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Container>
  );
};

export default ClienteList;
























