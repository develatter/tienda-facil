package com.javalopers.tiendafacil.backend.exception;

public class DetailsNotFoundException extends RuntimeException {
    public DetailsNotFoundException(String message) {
        super(message);
    }
}
