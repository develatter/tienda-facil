package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.dto.OrderDTO;
import com.javalopers.tiendafacil.backend.model.Order;
import com.javalopers.tiendafacil.backend.repository.OrderRepository;
import com.javalopers.tiendafacil.backend.repository.OrderStatusRepository;
import com.javalopers.tiendafacil.backend.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public Order saveOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
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


    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        //TODO: Incluir repositorio de Customer y OrderStatus
        order.setOrderDate(orderDTO.getOrderDate());
        order.setDeliveryDate(orderDTO.getDeliveryDate());
        order.setStatus(
                orderStatusRepository.getReferenceById(orderDTO.getStatusId())
        );

        return order;
    }
}
