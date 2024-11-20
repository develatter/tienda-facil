package com.javalopers.tiendafacil.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@Schema(description = "Representa una orden realizada por un cliente")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la orden", example = "1")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @Schema(description = "Cliente que realizó la orden")
    private Customer customer;

    @Column(name = "order_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Schema(description = "Fecha y hora de la creación de la orden", example = "2024-11-01T12:00:00")
    private LocalDateTime orderDate;

    @Column(name = "delivery_date", nullable = false)
    @Schema(description = "Fecha de entrega de la orden", example = "2024-11-05")
    private LocalDate deliveryDate;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    @Schema(description = "Estado de la orden")
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Detalles de los productos en la orden")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (orderDate == null) {
            orderDate = LocalDateTime.now();
        }
    }

    public void addOrderDetails(OrderDetails details) {
        orderDetails.add(details);
        details.setOrder(this);
    }
}
