package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.OrderRequestDTO;
import com.javalopers.tiendafacil.backend.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Order Service", description = "Service for managing orders")
public interface OrderService {

    @Operation(summary = "Save a new order", description = "Creates and saves a new order based on the provided details")
    Order saveOrder(OrderRequestDTO orderRequestDTO);

    @Operation(summary = "Find an order by ID", description = "Retrieves an order by its unique ID")
    Order findOrderById(Integer orderId);

    @Operation(summary = "Update an order by ID", description = "Updates the details of an existing order by its ID")
    Order updateOrder(Integer orderId, OrderRequestDTO orderRequestDTO);

    @Operation(summary = "Delete an order by ID", description = "Deletes an order identified by its unique ID")
    void deleteOrderById(Integer orderId);

    @Operation(summary = "Retrieve all orders", description = "Fetches a list of all orders")
    List<Order> findAllOrders();
}
