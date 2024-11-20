package com.javalopers.tiendafacil.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
@Table(name = "order_status")
@Schema(description = "Estado de las órdenes en el sistema")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    @Schema(description = "ID único del estado de la orden", example = "1")
    private Integer statusId;

    @Column(name = "status",
            nullable = false,
            unique = true,
            length = 10)
    @Schema(description = "Estado de la orden, como 'PENDING', 'SHIPPED', etc.", example = "PENDING")
    private String status;
}
