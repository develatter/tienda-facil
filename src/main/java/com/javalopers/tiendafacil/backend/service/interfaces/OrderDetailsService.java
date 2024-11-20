package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.OrderDetailsRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Order Details Service", description = "Service for managing order details")
public interface OrderDetailsService {

    @Operation(summary = "Retrieve all order details", description = "Fetches a list of all order details")
    List<OrderDetailsRequestDTO> findAllDetails();

    @Operation(summary = "Get order detail by ID", description = "Fetches a specific order detail by its unique ID")
    OrderDetailsRequestDTO findDetailById(Integer id);

    @Operation(summary = "Save order details", description = "Creates and saves new order details for a given order")
    OrderDetailsRequestDTO saveDetails(OrderDetailsRequestDTO orderDetailsRequestDTO, Integer orderId);

    @Operation(summary = "Update order details by ID", description = "Updates the details of a specific order by its ID")
    OrderDetailsRequestDTO updateDetails(Integer id, OrderDetailsRequestDTO orderDetailsRequestDTO);

    @Operation(summary = "Delete order details by ID", description = "Deletes the order details identified by their unique ID")
    void deleteDetailsById(Integer id);
}
