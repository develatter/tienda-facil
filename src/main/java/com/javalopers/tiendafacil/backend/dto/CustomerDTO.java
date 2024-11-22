package com.javalopers.tiendafacil.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "CustomerDTO", description = "DTO para representar la información del cliente.")
public class CustomerDTO {

    @Schema(description = "ID único del cliente.", example = "123")
    private Integer customerId;

    @NotNull(message = "El Nombre del cliente es obligatorio")
    @Schema(description = "Primer nombre del cliente.", example = "Juan", required = true)
    private String firstName;

    @NotNull(message = "El Apellido del cliente es obligatorio")
    @Schema(description = "Apellido del cliente.", example = "Pérez", required = true)
    private String lastName;

    @NotNull(message = "La dirección cliente es obligatorio")
    @Schema(description = "Dirección del cliente.", example = "Calle Falsa 123, Ciudad Ficticia", required = true)
    private String address;

    @Email
    @NotNull(message = "El correo electrónico del Cliente es obligatorio")
    @Schema(description = "Correo electrónico del cliente.", example = "juan.perez@example.com", required = true)
    private String mail;

    @Schema(description = "Fecha de registro del cliente.", example = "2024-11-18T14:30:00")
    private LocalDateTime regDate;

    @Schema(description = "Estado del cliente (activo/inactivo).", example = "true", required = false)
    private Boolean active;
}
