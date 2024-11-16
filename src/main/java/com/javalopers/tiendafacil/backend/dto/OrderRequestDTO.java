package com.javalopers.tiendafacil.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class OrderRequestDTO {

    @NotNull(message = "El id del cliente es obligatorio")
    private Integer customerId;


    @NotNull(message = "La fecha de entrega es obligatoria")
    private LocalDate deliveryDate;


    private List<OrderDetailsRequestDTO> orderDetails;
}
