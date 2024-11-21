package com.javalopers.tiendafacil.backend.controller;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
import com.javalopers.tiendafacil.backend.dto.OrderDTO;
import com.javalopers.tiendafacil.backend.service.interfaces.ReportService;
import lombok.AllArgsConstructor;
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

    private final ReportService reportService;

    @GetMapping("/active-customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getActiveCustomers(){
        return reportService.getActiveCustomers();
    }

    @GetMapping("/pending-orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTO> getPendingOrders(){
        return reportService.getPendingOrders();
    }
}
