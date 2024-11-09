package com.javalopers.tiendafacil.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDetailsDTO {

    private Integer detailsId;

    @NotNull(message = "El id de la orden es requerido")
    private Integer orderId;

    @NotNull(message = "El id del producto es requerido")
    private Integer productId;

    @NotNull(message = "La cantidad del producto es requerida")
    private Integer productAmount;
}
