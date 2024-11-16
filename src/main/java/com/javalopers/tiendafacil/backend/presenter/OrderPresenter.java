package com.javalopers.tiendafacil.backend.presenter;

import com.javalopers.tiendafacil.backend.dto.OrderDetailsResponseDTO;
import com.javalopers.tiendafacil.backend.dto.OrderResponseDTO;
import com.javalopers.tiendafacil.backend.model.Order;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderPresenter {

    public OrderResponseDTO presentOrder(Order order) {
        return OrderResponseDTO.builder()
                .orderId(order.getOrderId())
                .customerName(order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName())
                .status(order.getStatus().getStatus())
                .deliveryDate(order.getDeliveryDate())
                .orderDate(order.getOrderDate())
                .orderDetails(order.getOrderDetails().stream()
                        .map(orderDetails -> OrderDetailsResponseDTO.builder()
                                .productId(orderDetails.getProduct().getProductId())
                                .productName(orderDetails.getProduct().getProductName())
                                .productAmount(orderDetails.getProductAmount())
                                .productPrice(orderDetails.getProduct().getUnitPrice())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}
