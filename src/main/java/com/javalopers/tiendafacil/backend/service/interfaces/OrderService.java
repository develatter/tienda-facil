package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.OrderDTO;
import com.javalopers.tiendafacil.backend.model.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(OrderDTO orderDTO);

    Order findOrderById(Integer orderId);

    Order updateOrder(Integer orderId, OrderDTO orderDTO);

    void deleteOrderById(Integer orderId);

    List<Order> findAllOrders();
}