package com.javalopers.tiendafacil.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@Schema(description = "Representa a un cliente de la tienda")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del cliente", example = "1")
    private Integer customerId;

    @Column(name = "first_name", nullable = false, length = 100)
    @Schema(description = "Nombre del cliente", example = "Juan")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 300)
    @Schema(description = "Apellido del cliente", example = "Pérez")
    private String lastName;

    @Column(nullable = false, length = 100)
    @Schema(description = "Dirección del cliente", example = "Calle Falsa 123")
    private String address;

    @Column(unique = true, nullable = false, length = 100)
    @Email
    @Schema(description = "Correo electrónico único del cliente", example = "juan.perez@correo.com")
    private String mail;

    @Column(name = "reg_date", nullable = false, updatable = false)
    @Schema(description = "Fecha de registro del cliente", example = "2024-11-01T12:00:00")
    private LocalDateTime regDate;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    @Schema(description = "Estado de activación del cliente", example = "false")
    private Boolean active = false;

    // Relación con Order
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @Schema(description = "Lista de órdenes realizadas por el cliente")
    private List<Order> orders;
}
