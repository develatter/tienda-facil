package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.dto.OrderDTO;
import com.javalopers.tiendafacil.backend.dto.OrderDetailsDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    private Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setOrderId(dto.getOrderId());
        setOrder(dto, order);

        List<OrderDetails> details = new ArrayList<>();
        if (dto.getOrderDetails() != null) {
            relateOrderDetails(dto, order, details);
        }
        order.setOrderDetails(details);

        return order;
    }

    private void updateEntityWithDTO(OrderDTO orderDTO, Order order) {
        setOrder(orderDTO, order);

        order.getOrderDetails().clear();

        List<OrderDetails> newDetails = new ArrayList<>();
        relateOrderDetails(orderDTO, order, newDetails);
        order.getOrderDetails().addAll(newDetails);
    }

    private void relateOrderDetails(OrderDTO orderDTO, Order order, List<OrderDetails> newDetails) {
        for (OrderDetailsDTO detailDTO : orderDTO.getOrderDetails()) {
            OrderDetails detail = new OrderDetails();
            detail.setProductAmount(detailDTO.getProductAmount());
            detail.setProduct(productRepository
                    .findById(detailDTO.getProductId())
                    .orElseThrow(() -> new NoSuchElementException(
                            "No se encuentra el producto con ID: " + detailDTO.getProductId()
                    )));
            detail.setOrder(order);
            newDetails.add(detail);
        }
    }

    private void setOrder(OrderDTO orderDTO, Order order) {
        order.setOrderDate(orderDTO.getOrderDate());
        order.setDeliveryDate(orderDTO.getDeliveryDate());

        order.setStatus(orderStatusRepository
                .findById(orderDTO.getStatusId())
                .orElseThrow(() -> new NoSuchElementException(
                        "No se encuentra el estado con ID: " + orderDTO.getStatusId()
                )));

        order.setCustomer(customerRepository
                .findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException(
                        "No se encuentra el cliente con ID: " + orderDTO.getCustomerId()
                )));
    }

    @Override
    @Transactional
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        order = orderRepository.save(order);
        return convertToDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO updateOrder(Integer orderId, OrderDTO orderDTO) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("No se encuentra el pedido con ID: " + orderId));

        updateEntityWithDTO(orderDTO, order);
        order = orderRepository.save(order);
        return convertToDTO(order);
    }

    @Override
    public OrderDTO findOrderById(Integer orderId) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() ->
                        new NoSuchElementException("No se encuentra el pedido con ID: " + orderId)
                );
        return convertToDTO(order);
    }


    @Override
    public void deleteOrderById(Integer orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new NoSuchElementException("Order not found with id: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<OrderDTO> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    private OrderDTO convertToDTO(Order entity) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(entity.getOrderId());
        dto.setOrderDate(entity.getOrderDate());
        dto.setDeliveryDate(entity.getDeliveryDate());
        dto.setStatusId(entity.getStatus().getStatusId());
        dto.setCustomerId(entity.getCustomer().getCustomerId());
        dto.setOrderDetails(entity.getOrderDetails()
                .stream()
                .map(this::convertOrderDetailsToDTO)
                .toList());
        return dto;
    }

    private OrderDetailsDTO convertOrderDetailsToDTO(OrderDetails orderDetails) {
        OrderDetailsDTO dto = new OrderDetailsDTO();
        dto.setOrderId(orderDetails.getOrder().getOrderId());
        dto.setDetailsId(orderDetails.getDetailsId());
        dto.setProductId(orderDetails.getProduct().getProductId());
        dto.setProductAmount(orderDetails.getProductAmount());
        return dto;
    }

}