package com.javalopers.tiendafacil.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerDTO {

    private Integer customerId;

    @NotNull(message = "El Nombre del cliente es obligatorio")
    private String firstName;

    @NotNull(message = "El Apellido del cliente es obligatorio")
    private String lastName;

    @NotNull(message = "La dirección cliente es obligatorio")
    private String address;

    @Email
    @NotNull(message = "El correo electrónico del Cliente es obligatorio")
    private String mail;

    private LocalDateTime regDate;

    @NotNull(message = "El estado del Cliente es obligatorio")
    private Boolean active;
}
