package com.javalopers.tiendafacil.backend.dto;

import com.javalopers.tiendafacil.backend.validation.ValidDeliveryDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ValidDeliveryDate
public class OrderDTO {

    private Integer orderId;

    @NotNull(message = "El id del cliente es obligatorio")
    private Integer customerId;

    @PastOrPresent(message = "La fecha del pedido no puede ser futura")
    private LocalDateTime orderDate;

    @NotNull(message = "La fecha de entrega es obligatoria")
    private LocalDate deliveryDate;

    @NotNull(message = "El estado del pedido no puede ser nulo")
    private Integer statusId;

    private List<OrderDetailsDTO> orderDetails = new ArrayList<>();
}
