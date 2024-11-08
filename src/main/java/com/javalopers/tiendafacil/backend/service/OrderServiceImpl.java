package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.model.Order;
import com.javalopers.tiendafacil.backend.repository.OrderRepository;
import com.javalopers.tiendafacil.backend.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findOrderById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
