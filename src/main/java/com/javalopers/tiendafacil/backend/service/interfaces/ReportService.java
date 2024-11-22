package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
import com.javalopers.tiendafacil.backend.dto.OrderResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Report Service", description = "Service for managing reports")
public interface ReportService {

   @Operation(summary = "Retrieve all active customers", description = "Fetches a list of all active customers")
   List<CustomerDTO> getActiveCustomers();

   @Operation(summary = "Retrieve all pending orders", description = "Fetches a list of all pending orders")
   List<OrderResponseDTO> getPendingOrders();
}
