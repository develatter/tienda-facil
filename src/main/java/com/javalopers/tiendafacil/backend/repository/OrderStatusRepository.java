package com.javalopers.tiendafacil.backend.repository;

import com.javalopers.tiendafacil.backend.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
}
