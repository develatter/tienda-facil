package com.javalopers.tiendafacil.backend.controller;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
import com.javalopers.tiendafacil.backend.service.interfaces.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@AllArgsConstructor
@Tag(name = "Clientes", description = "API REST CRUD para operaciones relacionadas con los clientes")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Crear un nuevo cliente", description = "Guarda un cliente en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida. Asegúrese de que los datos del cliente son correctos.",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveCustomer(
            @RequestBody
            @Parameter(description = "Datos del cliente a guardar.", required = true,
                    schema = @Schema(implementation = CustomerDTO.class, example = "{ \"name\": \"John Doe\", \"email\": \"john.doe@example.com\" }"))
            CustomerDTO customerRequest) {
        return customerService.saveCustomer(customerRequest);
    }

    /*
    TODO: REVISAR SI en "mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class)":
        1) hay que añadir antes de '@Schema' un @ArraySchema()
        2) Verificar si hay que relacionarla con la DTO o con la clase de Model
     */
    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista de todos los clientes registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor. Contacte al soporte técnico.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    @Operation(summary = "Obtener un cliente por su ID", description = "Devuelve los datos de un cliente según su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado. Verifique que el ID es correcto.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(
            @PathVariable
            @Parameter(description = "ID del cliente a consultar.", required = true, example = "123")
            Integer id) {
        return customerService.getCustomer(id);
    }

    @Operation(summary = "Actualizar un cliente existente", description = "Modifica los datos de un cliente ya registrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado. Verifique que el ID es correcto.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida. Asegúrese de que los datos actualizados son correctos.",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(
            @PathVariable
            @Parameter(description = "ID del cliente a actualizar.", required = true, example = "123")
            Integer id,
            @RequestBody
            @Parameter(description = "Datos del cliente actualizados.", required = true,
                    schema = @Schema(implementation = CustomerDTO.class, example = "{ \"name\": \"Jane Doe\", \"email\": \"jane.doe@example.com\" }"))
            CustomerDTO customerRequest) {
        return customerService.updateCustomer(id, customerRequest);
    }

    @Operation(summary = "Eliminar un cliente por su ID", description = "Elimina un cliente registrado en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente. No se retorna contenido."),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado. Verifique que el ID es correcto.",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(
            @PathVariable
            @Parameter(description = "ID del cliente a eliminar.", required = true, example = "123")
            Integer id) {
        customerService.deleteCustomer(id);
    }
}
