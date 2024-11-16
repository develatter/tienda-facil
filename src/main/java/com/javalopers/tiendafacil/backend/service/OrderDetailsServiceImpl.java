package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.dto.OrderDetailsRequestDTO;
import com.javalopers.tiendafacil.backend.exception.DetailsNotFoundException;
import com.javalopers.tiendafacil.backend.exception.OrderDoesNotExistsException;
import com.javalopers.tiendafacil.backend.exception.ProductNotFoundException;
import com.javalopers.tiendafacil.backend.model.Order;
import com.javalopers.tiendafacil.backend.model.OrderDetails;
import com.javalopers.tiendafacil.backend.repository.OrderDetailsRepository;
import com.javalopers.tiendafacil.backend.repository.OrderRepository;
import com.javalopers.tiendafacil.backend.repository.ProductRepository;
import com.javalopers.tiendafacil.backend.service.interfaces.OrderDetailsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private OrderDetailsRepository orderDetailsRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Override
    public List<OrderDetailsRequestDTO> findAllDetails() {
        return orderDetailsRepository
                .findAll()
                .stream()
                .map(OrderDetailsServiceImpl::convertToDTO)
                .toList();
    }

    @Override
    public OrderDetailsRequestDTO findDetailById(Integer id) {
        OrderDetails orderDetails = orderDetailsRepository
                .findById(id)
                .orElseThrow(() ->
                        new DetailsNotFoundException("No se encuentra el detalle del pedido con ID: " + id)
                );
        return convertToDTO(orderDetails);
    }

    @Override
    public OrderDetailsRequestDTO saveDetails(OrderDetailsRequestDTO orderDetailsRequestDTO, Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderDoesNotExistsException("No se encuentra el pedido con ID: " + orderId));
        OrderDetails orderDetailsEntity = convertToEntity(orderDetailsRequestDTO, order);
        orderDetailsRepository.save(orderDetailsEntity);
        return convertToDTO(orderDetailsEntity);
    }

    @Override
    public OrderDetailsRequestDTO updateDetails(Integer id, OrderDetailsRequestDTO orderDetailsRequestDTO) {
        OrderDetails orderDetailsEntity = orderDetailsRepository
                .findById(id)
                .orElseThrow(() ->
                        new DetailsNotFoundException("No se encuentra el detalle del pedido con ID: " + id)
                );
        updateEntityWithDTO(orderDetailsRequestDTO, orderDetailsEntity);
        orderDetailsRepository.save(orderDetailsEntity);
        return convertToDTO(orderDetailsEntity);
    }

    @Override
    public void deleteDetailsById(Integer id) {
        if (!orderDetailsRepository.existsById(id)) {
            throw new DetailsNotFoundException("No se encuentra el detalle del pedido con ID: " + id);
        }
        orderDetailsRepository.deleteById(id);
    }

    public static OrderDetailsRequestDTO convertToDTO(OrderDetails orderDetails) {
        return OrderDetailsRequestDTO.builder()
                .productId(orderDetails.getProduct().getProductId())
                .productAmount(orderDetails.getProductAmount())
                .build();
    }

    private OrderDetails convertToEntity(OrderDetailsRequestDTO orderDetailsRequestDTO, Order order) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrder(order);
        orderDetails.setProduct(productRepository.findById(orderDetailsRequestDTO.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "No se encuentra el producto con ID: " + orderDetailsRequestDTO.getProductId()
                        ))
        );
        orderDetails.setProductAmount(orderDetailsRequestDTO.getProductAmount());
        return orderDetails;
    }

    private void updateEntityWithDTO(OrderDetailsRequestDTO orderDetailsRequestDTO, OrderDetails orderDetails) {
        orderDetails.setProduct(productRepository.findById(orderDetailsRequestDTO.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "No se encuentra el producto con ID: " + orderDetailsRequestDTO.getProductId()
                        ))
        );
        orderDetails.setProductAmount(orderDetailsRequestDTO.getProductAmount());
    }
}