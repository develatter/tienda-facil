package com.javalopers.tiendafacil.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponseDTO {

    private Integer orderId;
    private String customerName;
    private String status;
    private LocalDate deliveryDate;
    private LocalDateTime orderDate;
    private List<OrderDetailsResponseDTO> orderDetails;
}
