package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.dto.OrderDetailsDTO;
import com.javalopers.tiendafacil.backend.exception.DetailsNotFoundException;
import com.javalopers.tiendafacil.backend.exception.OrderDoesNotExistsException;
import com.javalopers.tiendafacil.backend.exception.ProductNotFoundException;
import com.javalopers.tiendafacil.backend.model.OrderDetails;
import com.javalopers.tiendafacil.backend.repository.OrderDetailsRepository;
import com.javalopers.tiendafacil.backend.repository.OrderRepository;
import com.javalopers.tiendafacil.backend.repository.ProductRepository;
import com.javalopers.tiendafacil.backend.service.interfaces.OrderDetailsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private OrderDetailsRepository orderDetailsRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @Override
    public List<OrderDetailsDTO> findAllDetails() {
        return orderDetailsRepository
                .findAll()
                .stream()
                .map(OrderDetailsServiceImpl::convertToDTO)
                .toList();
    }

    @Override
    public OrderDetailsDTO findDetailById(Integer id) {
        OrderDetails orderDetails = orderDetailsRepository
                .findById(id)
                .orElseThrow(() ->
                        new DetailsNotFoundException("No se encuentra el detalle del pedido con ID: " + id)
                );
        return convertToDTO(orderDetails);
    }

    @Override
    public OrderDetailsDTO saveDetails(OrderDetailsDTO orderDetails) {
        OrderDetails orderDetailsEntity = convertToEntity(orderDetails);
        orderDetailsRepository.save(orderDetailsEntity);
        return convertToDTO(orderDetailsEntity);
    }

    @Override
    public OrderDetailsDTO updateDetails(Integer id, OrderDetailsDTO orderDetails) {
        OrderDetails orderDetailsEntity = orderDetailsRepository
                .findById(id)
                .orElseThrow(() ->
                        new DetailsNotFoundException("No se encuentra el detalle del pedido con ID: " + id)
                );
        updateEntityWithDTO(orderDetails, orderDetailsEntity);
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

    public static OrderDetailsDTO convertToDTO(OrderDetails orderDetails) {

        return OrderDetailsDTO.builder()
                .detailsId(orderDetails.getDetailsId())
                .orderId(orderDetails.getOrder().getOrderId())
                .productId(orderDetails.getProduct().getProductId())
                .productAmount(orderDetails.getProductAmount())
                .build();
    }

    private OrderDetails convertToEntity(OrderDetailsDTO orderDetailsDTO) {
        OrderDetails orderDetails = new OrderDetails();
        updateEntityWithDTO(orderDetailsDTO, orderDetails);
        orderDetails.setDetailsId(orderDetailsDTO.getDetailsId());
        return orderDetails;
    }

    private void updateEntityWithDTO(OrderDetailsDTO orderDetailsDTO, OrderDetails orderDetails) {
        orderDetails.setOrder(orderRepository
                .findById(orderDetailsDTO.getOrderId())
                .orElseThrow(() ->
                        new OrderDoesNotExistsException(
                                "No se encuentra el pedido con ID: " + orderDetailsDTO.getOrderId()
                        ))
        );
        orderDetails.setProduct(productRepository
                .findById(orderDetailsDTO.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "No se encuentra el producto con ID: " + orderDetailsDTO.getProductId()
                        ))
        );
        orderDetails.setProductAmount(orderDetailsDTO.getProductAmount());
    }


}
