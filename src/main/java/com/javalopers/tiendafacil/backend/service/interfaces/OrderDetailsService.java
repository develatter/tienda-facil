package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.OrderDetailsDTO;
import com.javalopers.tiendafacil.backend.model.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsService {

    List<OrderDetailsDTO> findAllDetails();
    OrderDetailsDTO findDetailById(Integer id);
    OrderDetailsDTO saveDetails(OrderDetailsDTO orderDetails);
    OrderDetailsDTO updateDetails(Integer id, OrderDetailsDTO orderDetails);
    void deleteDetailsById(Integer id);
}
