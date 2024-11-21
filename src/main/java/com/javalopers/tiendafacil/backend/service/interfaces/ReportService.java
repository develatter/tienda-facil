package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
import com.javalopers.tiendafacil.backend.dto.OrderDTO;

import java.util.List;


public interface ReportService {

   List<CustomerDTO> getActiveCustomers();

   List<OrderDTO> getPendingOrders();
}
