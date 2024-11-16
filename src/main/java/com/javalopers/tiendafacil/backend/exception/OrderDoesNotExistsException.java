package com.javalopers.tiendafacil.backend.exception;

public class OrderDoesNotExistsException extends RuntimeException {
    public OrderDoesNotExistsException(String message) {
        super(message);
    }
}
