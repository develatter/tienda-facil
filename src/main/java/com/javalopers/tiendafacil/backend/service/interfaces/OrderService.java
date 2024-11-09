package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO saveOrder(OrderDTO orderDTO);

    OrderDTO findOrderById(Integer orderId);

    OrderDTO updateOrder(Integer orderId, OrderDTO orderDTO);

    void deleteOrderById(Integer orderId);

    List<OrderDTO> findAllOrders();
}