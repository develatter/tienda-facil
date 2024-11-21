package com.javalopers.tiendafacil.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderRequestDTO {

    @NotNull(message = "El id del cliente es obligatorio")
    @Schema(description = "El identificador único del cliente que realiza la orden", example = "123")
    private Integer customerId;

    @NotNull(message = "La fecha de entrega es obligatoria")
    @Schema(description = "La fecha en que se debe entregar la orden", example = "2024-12-25")
    private LocalDate deliveryDate;

    @Schema(description = "Los detalles de los productos en la orden", implementation = OrderDetailsRequestDTO.class)
    private List<OrderDetailsRequestDTO> orderDetails;
}
