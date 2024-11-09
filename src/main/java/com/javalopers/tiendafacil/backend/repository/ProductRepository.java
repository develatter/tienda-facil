package com.javalopers.tiendafacil.backend.repository;

import com.javalopers.tiendafacil.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
