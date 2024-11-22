package com.javalopers.tiendafacil.backend.repository;

import com.javalopers.tiendafacil.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    boolean existsByCustomer_CustomerIdAndOrderDateAfter (Integer customerId, LocalDateTime orderDate);

    @Query(value = "SELECT * FROM orders WHERE status_id = '1'", nativeQuery = true)
    List<Order> findPendingOrder();
}

