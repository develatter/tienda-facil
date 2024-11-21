package com.javalopers.tiendafacil.backend.repository;

import com.javalopers.tiendafacil.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders WHERE status = 'Pendiente'", nativeQuery = true)
    List<Order> findPendingOrder();
}

