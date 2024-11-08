package com.javalopers.tiendafacil.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    private Integer customerId;
}
