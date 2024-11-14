package com.javalopers.tiendafacil.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Integer productId;

    @NotNull(message = "El nombre del Producto es obligatorio")
    private String productName;

    @NotNull(message = "La descripción del producto es obligatoria y no puede duplicarse")
    private String productDescription;

    @NotNull(message = "El precio unitario es obligatorio")
    private BigDecimal unitPrice;

    @NotNull(message = "Ingresar la cantidad de productos es obligatorio")
    private Integer currentStock;
}
