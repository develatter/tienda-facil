package com.javalopers.tiendafacil.backend.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(
            name = "product_name",
            nullable = false,
            length = 100
    )
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    @Column(
            name = "unit_price",
            nullable = false
    )
    private BigDecimal unitPrice;

    @Column(
            name = "current_stock",
            nullable = false
    )
    private Integer currentStock;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderDetails> orderDetails;


}
