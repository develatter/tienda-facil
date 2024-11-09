package com.javalopers.tiendafacil.backend.repository;

import com.javalopers.tiendafacil.backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer,Integer> {

    boolean existsByMail (String mail);
}
