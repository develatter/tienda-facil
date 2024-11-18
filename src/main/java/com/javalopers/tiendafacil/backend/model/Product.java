package com.javalopers.tiendafacil.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@Schema(description = "Representación de un producto en el sistema")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @Schema(description = "ID único del producto", example = "1")
    private Integer productId;

    @Column(name = "product_name", nullable = false, length = 100)
    @Schema(description = "Nombre del producto", example = "Camiseta")
    private String productName;

    @Column(name = "product_description")
    @Schema(description = "Descripción detallada del producto", example = "Camiseta de algodón 100% para adultos")
    private String productDescription;

    @Column(name = "unit_price", nullable = false)
    @Schema(description = "Precio unitario del producto", example = "19.99")
    private BigDecimal unitPrice;

    @Column(name = "current_stock", nullable = false)
    @Schema(description = "Cantidad actual de productos en stock", example = "150")
    private Integer currentStock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Lista de detalles de órdenes asociados con el producto")
    private List<OrderDetails> orderDetails;
}
