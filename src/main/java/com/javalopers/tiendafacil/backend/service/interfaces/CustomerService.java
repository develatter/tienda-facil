package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Customer Service", description = "Service for customer operations")
public interface CustomerService {

    @Operation(summary = "Save a new customer", description = "Creates and saves a new customer using the provided DTO")
    CustomerDTO saveCustomer(CustomerDTO customerRequest);

    @Operation(summary = "Retrieve all customers", description = "Fetches a list of all customers")
    List<CustomerDTO> getAllCustomer();

    @Operation(summary = "Get customer by ID", description = "Fetches a customer by their unique ID")
    CustomerDTO getCustomer(Integer id);

    @Operation(summary = "Update customer by ID", description = "Updates the details of a customer identified by their ID")
    CustomerDTO updateCustomer(Integer id, CustomerDTO customerRequest);

    @Operation(summary = "Delete customer by ID", description = "Deletes a customer identified by their unique ID")
    void deleteCustomer(Integer id);
}
