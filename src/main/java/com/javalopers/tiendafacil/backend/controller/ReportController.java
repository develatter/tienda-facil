package com.javalopers.tiendafacil.backend.controller;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
import com.javalopers.tiendafacil.backend.dto.OrderResponseDTO;
import com.javalopers.tiendafacil.backend.service.interfaces.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
public class ReportController {

    @Autowired
    private final ReportService reportService;

    @Operation(summary = "Obtener todos los clientes activos", description = "Devuelve una lista de todos los clientes activos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/active-customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getActiveCustomers(){
        return reportService.getActiveCustomers();
    }

    @Operation(summary = "Obtener todos las ordenes pendientes", description = "Devuelve una lista con todas las ordenes pendientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/pending-orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponseDTO> getPendingOrders(){
        return reportService.getPendingOrders();
    }
}
