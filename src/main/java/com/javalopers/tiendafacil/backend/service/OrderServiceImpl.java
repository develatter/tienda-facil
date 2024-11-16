package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.dto.OrderDTO;
import com.javalopers.tiendafacil.backend.dto.OrderDetailsDTO;
import com.javalopers.tiendafacil.backend.exception.*;
import com.javalopers.tiendafacil.backend.model.Order;
import com.javalopers.tiendafacil.backend.model.OrderDetails;
import com.javalopers.tiendafacil.backend.repository.CustomerRepository;
import com.javalopers.tiendafacil.backend.repository.OrderRepository;
import com.javalopers.tiendafacil.backend.repository.OrderStatusRepository;
import com.javalopers.tiendafacil.backend.repository.ProductRepository;
import com.javalopers.tiendafacil.backend.service.interfaces.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;


    @Override
    @Transactional
    public Order saveOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        order = orderRepository.save(order);
        return order;
    }

    @Override
    @Transactional
    public Order updateOrder(Integer orderId, OrderDTO orderDTO) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new OrderDoesNotExistsException("No se encuentra el pedido con ID: " + orderId));

        updateEntityWithDTO(orderDTO, order);
        order = orderRepository.save(order);
        return order;

    }

    @Override
    @Transactional
    public Order findOrderById(Integer orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() ->
                        new OrderDoesNotExistsException("No se encuentra el pedido con ID: " + orderId)
                );
    }


    @Override
    @Transactional
    public void deleteOrderById(Integer orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderDoesNotExistsException("No se encuentra el pedido con ID: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    private OrderDTO convertToDTO(Order entity) {
        return OrderDTO.builder()
                .orderId(entity.getOrderId())
                .orderDate(entity.getOrderDate())
                .deliveryDate(entity.getDeliveryDate())
                .statusId(entity.getStatus().getStatusId())
                .customerId(entity.getCustomer().getCustomerId())
                .orderDetails(entity.getOrderDetails()
                        .stream()
                        .map(this::convertOrderDetailsToDTO)
                        .toList())
                .build();
    }

    private OrderDetailsDTO convertOrderDetailsToDTO(OrderDetails orderDetails) {
        return OrderDetailsDTO.builder()
                .orderId(orderDetails.getOrder().getOrderId())
                .productId(orderDetails.getProduct().getProductId())
                .detailsId(orderDetails.getDetailsId())
                .productAmount(orderDetails.getProductAmount())
                .build();
    }


    private Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setOrderId(dto.getOrderId());
        setOrderProperties(dto, order);

        if (dto.getOrderDetails() != null) {
            dto.getOrderDetails().forEach(
                    detailDTO -> addOrderDetail(order, detailDTO)
            );
        }
        return order;
    }

    private void addOrderDetail(Order order, OrderDetailsDTO detailDTO) {
        OrderDetails detail = new OrderDetails();
        detail.setProductAmount(detailDTO.getProductAmount());
        detail.setProduct(productRepository
                .findById(detailDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(
                        "No se encuentra el producto con ID: " + detailDTO.getProductId()
                )));
        order.addOrderDetails(detail);
    }

    private void updateEntityWithDTO(OrderDTO orderDTO, Order order) {
        setOrderProperties(orderDTO, order);

        order.getOrderDetails().forEach(order::removeOrderDetails);
        orderDTO.getOrderDetails().forEach(
                detailDTO -> addOrderDetail(order, detailDTO)
        );

    }


    private void setOrderProperties(OrderDTO orderDTO, Order order) {
        order.setOrderDate(orderDTO.getOrderDate());
        order.setDeliveryDate(orderDTO.getDeliveryDate());
        order.setStatus(orderStatusRepository
                .findById(orderDTO.getStatusId())
                .orElseThrow(() -> new StatusNotFoundException(
                        "No se encuentra el estado con ID: " + orderDTO.getStatusId()
                )));
        order.setCustomer(customerRepository
                .findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new CustomerDoesNotExistsException(
                        "No se encuentra el cliente con ID: " + orderDTO.getCustomerId()
                )));
    }

}