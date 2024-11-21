// src/Order/OrderList.js
import React, { useEffect, useState } from 'react';
import { getAllOrders } from '../services/OrderService';  // Importar correctamente la función

const OrderList = () => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const data = await getAllOrders();  // Llamada al servicio para obtener los pedidos
        setOrders(data);  // Guardar los pedidos en el estado
      } catch (error) {
        console.error("Error fetching orders:", error);
      } finally {
        setLoading(false);  // Desactivar el estado de carga
      }
    };

    fetchOrders();
  }, []);  // El array vacío significa que esto solo se ejecutará una vez al montar el componente

  if (loading) {
    return <div>Loading...</div>;  // Mostrar un mensaje de carga mientras obtenemos los datos
  }

  return (
    <div>
      <h2>Lista de Pedidos</h2>
      <ul>
        {orders.map(order => (
          <li key={order.id}>
            {order.customerName} - {order.orderDate} - ${order.totalAmount}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default OrderList;


















