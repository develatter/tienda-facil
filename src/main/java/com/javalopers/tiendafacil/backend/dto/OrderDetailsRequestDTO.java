package com.javalopers.tiendafacil.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailsRequestDTO {

    @NotNull(message = "El id de la orden no puede ser omitido")
    @Schema(description = "El identificador único de la orden", example = "123", required = true)
    private Integer orderId;

    @NotNull(message = "El id del producto es requerido")
    @Schema(description = "El identificador único del producto", example = "456", required = true)
    private Integer productId;

    @NotNull(message = "La cantidad del producto es requerida")
    @Schema(description = "La cantidad del producto solicitada", example = "2", required = true)
    private Integer productAmount;
}
