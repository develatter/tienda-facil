package com.javalopers.tiendafacil.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderDetailsResponseDTO {
    private Integer productId;
    private String productName;
    private Integer productAmount;
    private BigDecimal productPrice;
}
