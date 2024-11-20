package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Product Service", description = "Service for managing products")
public interface ProductService {

    @Operation(summary = "Save a new product", description = "Creates and saves a new product based on the provided details")
    ProductDTO saveProduct(ProductDTO productRequest);

    @Operation(summary = "Retrieve all products", description = "Fetches a list of all products")
    List<ProductDTO> getAllProducts();

    @Operation(summary = "Find a product by ID", description = "Retrieves a product by its unique ID")
    ProductDTO getProduct(Integer id);

    @Operation(summary = "Update a product by ID", description = "Updates the details of an existing product by its ID")
    ProductDTO updateProduct(Integer id, ProductDTO productRequest);

    @Operation(summary = "Delete a product by ID", description = "Deletes a product identified by its unique ID")
    void deleteProduct(Integer id);
}
