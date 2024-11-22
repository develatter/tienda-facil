package com.javalopers.tiendafacil.backend.controller;

import com.javalopers.tiendafacil.backend.dto.ProductDTO;
import com.javalopers.tiendafacil.backend.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@AllArgsConstructor
@Tag(name = "Products", description = "Operaciones relacionadas con los productos")
public class ProductoController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Crear un nuevo producto", description = "Guarda un nuevo producto en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida. Verifique los datos del producto.",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO saveProduct(
            @Valid @RequestBody
            @Parameter(description = "Datos del producto a guardar.", required = true,
                    schema = @Schema(implementation = ProductDTO.class))
            ProductDTO productRequest) {
        return productService.saveProduct(productRequest);
    }

    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de todos los productos registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Obtener un producto por su ID", description = "Devuelve los datos de un producto específico por su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado. Verifique que el ID es correcto.",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(
            @PathVariable
            @Parameter(description = "ID del producto a consultar.", required = true, example = "1")
            Integer id) {
        return productService.getProduct(id);
    }

    @Operation(summary = "Actualizar un producto existente", description = "Modifica los datos de un producto registrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida. Verifique los datos del producto.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(
            @Valid @PathVariable
            @Parameter(description = "ID del producto a actualizar.", required = true, example = "1")
            Integer id,
            @RequestBody
            @Parameter(description = "Datos actualizados del producto.", required = true,
                    schema = @Schema(implementation = ProductDTO.class))
            ProductDTO productRequest) {
        return productService.updateProduct(id, productRequest);
    }

    @Operation(summary = "Eliminar un producto por su ID", description = "Elimina un producto de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente."),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado.",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(
            @PathVariable(value = "id")
            @Parameter(description = "ID del producto a eliminar.", required = true, example = "1")
            Integer id) {
        productService.deleteProduct(id);
    }
}
