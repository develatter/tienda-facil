package com.javalopers.tiendafacil.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    @Schema(description = "El identificador único del producto", example = "123")
    private Integer productId;

    @Schema(description = "El nombre del producto", example = "Camiseta Deportiva")
    @NotNull(message = "El nombre del Producto es obligatorio")
    private String productName;

    @Schema(description = "La descripción del producto", example = "Camiseta de algodón para actividad deportiva")
    @NotNull(message = "La descripción del producto es obligatoria y no puede duplicarse")
    private String productDescription;

    @Schema(description = "El precio unitario del producto", example = "19.99")
    @NotNull(message = "El precio unitario es obligatorio")
    private BigDecimal unitPrice;

    @Schema(description = "La cantidad actual de productos en inventario", example = "50")
    @NotNull(message = "Ingresar la cantidad de productos es obligatorio")
    private Integer currentStock;
}
