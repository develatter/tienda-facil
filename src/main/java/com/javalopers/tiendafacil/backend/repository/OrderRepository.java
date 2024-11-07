package com.javalopers.tiendafacil.backend.repository;

import com.javalopers.tiendafacil.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
