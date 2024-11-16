package com.javalopers.tiendafacil.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailsRequestDTO {

    @NotNull(message = "El id de la orden no puede ser omitido")
    private Integer orderId;

    @NotNull(message = "El id del producto es requerido")
    private Integer productId;

    @NotNull(message = "La cantidad del producto es requerida")
    private Integer productAmount;
}
