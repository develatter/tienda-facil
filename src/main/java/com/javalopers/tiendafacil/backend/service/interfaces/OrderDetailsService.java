package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.OrderDetailsRequestDTO;

import java.util.List;

public interface OrderDetailsService {
    List<OrderDetailsRequestDTO> findAllDetails();
    OrderDetailsRequestDTO findDetailById(Integer id);
    OrderDetailsRequestDTO saveDetails(OrderDetailsRequestDTO orderDetailsRequestDTO, Integer orderId);
    OrderDetailsRequestDTO updateDetails(Integer id, OrderDetailsRequestDTO orderDetailsRequestDTO);
    void deleteDetailsById(Integer id);
}