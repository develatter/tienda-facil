package com.javalopers.tiendafacil.backend.controller;

import com.javalopers.tiendafacil.backend.dto.OrderRequestDTO;
import com.javalopers.tiendafacil.backend.dto.OrderDetailsResponseDTO;
import com.javalopers.tiendafacil.backend.dto.OrderResponseDTO;
import com.javalopers.tiendafacil.backend.model.Order;
import com.javalopers.tiendafacil.backend.presenter.OrderPresenter;
import com.javalopers.tiendafacil.backend.service.interfaces.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
@Tag(name = "Orders", description = "Operaciones relacionadas con las órdenes")
public class OrderController {

    private final OrderService orderService;
    private final OrderPresenter orderPresenter;

    @Operation(summary = "Crear una nueva orden", description = "Guarda una nueva orden en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Orden creada exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida. Verifique los datos de la orden.",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(
            @Valid @RequestBody
            @Parameter(description = "Datos de la orden a guardar.", required = true,
                    schema = @Schema(implementation = OrderRequestDTO.class, example = "{ \"customerId\": 1, \"products\": [...] }"))
            OrderRequestDTO orderRequestDTO) {
        Order savedOrder = orderService.saveOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderPresenter.presentOrder(savedOrder));
    }

    @Operation(summary = "Obtener todas las órdenes", description = "Devuelve una lista de todas las órdenes registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de órdenes obtenida exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.findAllOrders().stream()
                .map(orderPresenter::presentOrder)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "Obtener una orden por su ID", description = "Devuelve los datos de una orden específica por su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden encontrada exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada. Verifique que el ID es correcto.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(
            @PathVariable
            @Parameter(description = "ID de la orden a consultar.", required = true, example = "1")
            Integer id) {
        OrderResponseDTO order = orderPresenter.presentOrder(orderService.findOrderById(id));
        return ResponseEntity.ok(order);
    }

    @Operation(summary = "Actualizar una orden existente", description = "Modifica los datos de una orden registrada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden actualizada exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida. Verifique los datos de la orden.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada.",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(
            @PathVariable
            @Parameter(description = "ID de la orden a actualizar.", required = true, example = "1")
            Integer id,
            @Valid @RequestBody
            @Parameter(description = "Datos actualizados de la orden.", required = true,
                    schema = @Schema(implementation = OrderRequestDTO.class, example = "{ \"customerId\": 2, \"products\": [...] }"))
            OrderRequestDTO orderRequestDTO) {
        Order updatedOrder = orderService.updateOrder(id, orderRequestDTO);
        OrderResponseDTO orderResponseDTO = orderPresenter.presentOrder(updatedOrder);
        return ResponseEntity.ok(orderResponseDTO);
    }

    @Operation(summary = "Eliminar una orden por su ID", description = "Elimina una orden de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Orden eliminada exitosamente."),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada.",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteOrder(
            @PathVariable
            @Parameter(description = "ID de la orden a eliminar.", required = true, example = "1")
            Integer id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("El registro con id " + id + " fue eliminado con éxito.");
    }

    @Operation(summary = "Obtener los detalles de una orden", description = "Devuelve los detalles de una orden específica por su identificador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles de la orden obtenidos exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDetailsResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}/details")
    public ResponseEntity<List<OrderDetailsResponseDTO>> getOrderDetails(
            @PathVariable
            @Parameter(description = "ID de la orden para consultar sus detalles.", required = true, example = "1")
            Integer id) {
        OrderResponseDTO order = orderPresenter.presentOrder(orderService.findOrderById(id));
        List<OrderDetailsResponseDTO> details = order.getOrderDetails();
        return ResponseEntity.ok(details);
    }
}
