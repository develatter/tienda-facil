package com.javalopers.tiendafacil.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
@Table(name = "order_details")
@Schema(description = "Detalles de los productos dentro de una orden")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "details_id")
    @Schema(description = "ID único de los detalles de la orden", example = "1")
    private Integer detailsId;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            nullable = false
    )
    @Schema(description = "Orden asociada a estos detalles")
    private Order order;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false
    )
    @Schema(description = "Producto asociado a este detalle")
    private Product product;

    @Column(
            name = "product_amount",
            nullable = false
    )
    @Schema(description = "Cantidad de productos en este detalle", example = "2")
    private Integer productAmount;
}
