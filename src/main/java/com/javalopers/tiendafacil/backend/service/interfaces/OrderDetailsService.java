package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.model.OrderDetails;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsService {

    List<OrderDetails> findAll();
    Optional<OrderDetails> findById(Integer id);
    OrderDetails save(OrderDetails orderDetails);
    void deleteById(Integer id);
}
