package com.javalopers.tiendafacil.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponseDTO {

    @Schema(description = "El identificador único de la orden", example = "456")
    private Integer orderId;

    @Schema(description = "El nombre del cliente que realizó la orden", example = "Juan Pérez")
    private String customerName;

    @Schema(description = "El estado actual de la orden", example = "Pendiente")
    private String status;

    @Schema(description = "La fecha programada para la entrega de la orden", example = "2024-12-25")
    private LocalDate deliveryDate;

    @Schema(description = "La fecha y hora en que se realizó la orden", example = "2024-11-18T14:30:00")
    private LocalDateTime orderDate;

    @Schema(description = "Los detalles de los productos en la orden", implementation = OrderDetailsResponseDTO.class)
    private List<OrderDetailsResponseDTO> orderDetails;
}
