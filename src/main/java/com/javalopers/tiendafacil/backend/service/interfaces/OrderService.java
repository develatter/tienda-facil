package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order saveOrder(Order order);

    Optional<Order> findOrderById(Integer orderId);

    void deleteOrderById(Integer orderId);

    List<Order> getAllOrders();
}
