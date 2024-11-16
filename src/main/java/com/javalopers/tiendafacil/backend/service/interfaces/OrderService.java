package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.OrderRequestDTO;
import com.javalopers.tiendafacil.backend.model.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(OrderRequestDTO orderRequestDTO);

    Order findOrderById(Integer orderId);

    Order updateOrder(Integer orderId, OrderRequestDTO orderRequestDTO);

    void deleteOrderById(Integer orderId);

    List<Order> findAllOrders();
}