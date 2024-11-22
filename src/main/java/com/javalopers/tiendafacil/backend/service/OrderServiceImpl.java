package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.dto.OrderRequestDTO;
import com.javalopers.tiendafacil.backend.dto.OrderDetailsRequestDTO;
import com.javalopers.tiendafacil.backend.exception.CustomerDoesNotExistsException;
import com.javalopers.tiendafacil.backend.exception.OrderDoesNotExistsException;
import com.javalopers.tiendafacil.backend.exception.ProductNotFoundException;
import com.javalopers.tiendafacil.backend.exception.StatusNotFoundException;
import com.javalopers.tiendafacil.backend.model.Order;
import com.javalopers.tiendafacil.backend.model.OrderDetails;
import com.javalopers.tiendafacil.backend.repository.CustomerRepository;
import com.javalopers.tiendafacil.backend.repository.OrderRepository;
import com.javalopers.tiendafacil.backend.repository.OrderStatusRepository;
import com.javalopers.tiendafacil.backend.repository.ProductRepository;
import com.javalopers.tiendafacil.backend.service.interfaces.CustomerService;
import com.javalopers.tiendafacil.backend.service.interfaces.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerService customerService;
    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Order saveOrder(OrderRequestDTO orderRequestDTO) {
        Order order = convertToEntity(orderRequestDTO);
        order = orderRepository.save(order);
        saveOrderDetails(order, orderRequestDTO.getOrderDetails());
        customerService.activateCustomer(order.getCustomer().getCustomerId());
        return order;
    }

    @Override
    @Transactional
    public Order updateOrder(Integer orderId, OrderRequestDTO orderRequestDTO) {
        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new OrderDoesNotExistsException("No se encuentra el pedido con ID: " + orderId));

        updateEntityWithDTO(orderRequestDTO, order);
        order = orderRepository.save(order);
        return order;
    }

    @Override
    @Transactional
    public Order findOrderById(Integer orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new OrderDoesNotExistsException("No se encuentra el pedido con ID: " + orderId));
    }

    @Override
    @Transactional
    public void deleteOrderById(Integer orderId) {

        if (!orderRepository.existsById(orderId)) {
            throw new OrderDoesNotExistsException("No se encuentra el pedido con ID: " + orderId);
        }

        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderDoesNotExistsException("No se encuentra el pedido con ID: " + orderId));

        Integer customerId = order.getCustomer().getCustomerId();
        orderRepository.deleteById(orderId);
        customerService.deactivateCustomerIfNoRecentOrders(customerId);
    }

    @Override
    @Transactional
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    private Order convertToEntity(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setCustomer(customerRepository.findById(orderRequestDTO.getCustomerId())
                .orElseThrow(() -> new CustomerDoesNotExistsException("El cliente no se encuentra en la base de datos.")));
        order.setDeliveryDate(orderRequestDTO.getDeliveryDate());
        order.setStatus(orderStatusRepository.findById(1)
                .orElseThrow(() -> new StatusNotFoundException("El estado no ha sido encontrado")));
        return order;
    }

    private void updateEntityWithDTO(OrderRequestDTO orderRequestDTO, Order order) {
        order.setCustomer(customerRepository.findById(orderRequestDTO.getCustomerId())
                .orElseThrow(() ->new CustomerDoesNotExistsException("El cliente no se encuentra en la base de datos.")));
        order.setDeliveryDate(orderRequestDTO.getDeliveryDate());
        order.setStatus(orderStatusRepository.findById(1)
                .orElseThrow(() ->  new StatusNotFoundException("El estado no ha sido encontrado")));

        order.getOrderDetails().clear();
        saveOrderDetails(order, orderRequestDTO.getOrderDetails());
    }

    private void saveOrderDetails(Order order, List<OrderDetailsRequestDTO> orderDetailsRequestDTOs) {
        orderDetailsRequestDTOs.forEach(detailDTO -> {
            OrderDetails detail = new OrderDetails();
            detail.setOrder(order);
            detail.setProductAmount(detailDTO.getProductAmount());
            detail.setProduct(productRepository.findById(detailDTO.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("No se encuentra el producto con ID: " + detailDTO.getProductId())));
            order.addOrderDetails(detail);
        });
    }
}