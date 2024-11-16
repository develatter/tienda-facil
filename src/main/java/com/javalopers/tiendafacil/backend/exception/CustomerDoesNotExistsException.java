package com.javalopers.tiendafacil.backend.exception;

public class CustomerDoesNotExistsException extends RuntimeException {
    public CustomerDoesNotExistsException(String message) {
        super(message);
    }
}
