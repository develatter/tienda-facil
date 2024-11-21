package com.javalopers.tiendafacil.backend.repository;

import com.javalopers.tiendafacil.backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository <Customer,Integer> {

    boolean existsByMail (String mail);

    @Query(value = "SELECT c.customer_id, c.first_name, c.last_name, c.address, c.mail, c.reg_date, " +
            "CASE WHEN MAX(o.order_date) >= CURRENT_DATE - 14 THEN TRUE ELSE FALSE END AS active " +
            "FROM customers c INNER JOIN orders o ON c.customer_id = o.customer_id " +
            "GROUP BY c.customer_id, c.first_name, c.last_name, c.address, c.mail, c.reg_date",
            nativeQuery = true)
    List<Object[]> findActiveCustomers();





}
