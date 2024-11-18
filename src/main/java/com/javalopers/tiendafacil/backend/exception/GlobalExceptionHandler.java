package com.javalopers.tiendafacil.backend.exception;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Operation(summary = "Handle Customer Does Not Exist Exception", description = "Handles situations where a customer is not found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ExceptionHandler(CustomerDoesNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleCustomerDoesNotExistsException(CustomerDoesNotExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @Operation(summary = "Handle Product Not Found Exception", description = "Handles situations where a product is not found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @Operation(summary = "Handle Details Not Found Exception", description = "Handles situations where order details are not found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Details not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ExceptionHandler(DetailsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleDetailsNotFoundException(DetailsNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @Operation(summary = "Handle Order Does Not Exist Exception", description = "Handles situations where an order does not exist.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ExceptionHandler(OrderDoesNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleOrderDoesNotExistsException(OrderDoesNotExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @Operation(summary = "Handle Status Not Found Exception", description = "Handles situations where a status is not found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Status not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ExceptionHandler(StatusNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleStatusNotFoundException(StatusNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @Operation(summary = "Handle Customer Already Exists Exception", description = "Handles situations where a customer already exists.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Conflict - Customer already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @Operation(summary = "Handle Method Argument Type Mismatch Exception", description = "Handles invalid URL or method argument type.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request - Invalid argument type"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe la url solicitada");
    }

    @Operation(summary = "Handle Resource Not Found Exception", description = "Handles cases where a resource is not found.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la url solicitada :C");
    }
}
