package com.javalopers.tiendafacil.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderDetailsResponseDTO {

    @Schema(description = "El identificador único del producto", example = "456")
    private Integer productId;

    @Schema(description = "El nombre del producto", example = "Camiseta de Algodón")
    private String productName;

    @Schema(description = "La cantidad del producto en la orden", example = "2")
    private Integer productAmount;

    @Schema(description = "El precio unitario del producto", example = "19.99", format = "decimal")
    private BigDecimal productPrice;
}
