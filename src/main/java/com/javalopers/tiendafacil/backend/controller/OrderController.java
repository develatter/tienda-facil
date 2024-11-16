package com.javalopers.tiendafacil.backend.controller;

import com.javalopers.tiendafacil.backend.dto.OrderDTO;
import com.javalopers.tiendafacil.backend.dto.OrderDetailsResponseDTO;
import com.javalopers.tiendafacil.backend.dto.OrderResponseDTO;
import com.javalopers.tiendafacil.backend.exception.OrderDoesNotExistsException;
import com.javalopers.tiendafacil.backend.model.Order;
import com.javalopers.tiendafacil.backend.presenter.OrderPresenter;
import com.javalopers.tiendafacil.backend.service.interfaces.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderPresenter orderPresenter;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        Order savedOrder = orderService.saveOrder(orderDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderPresenter.presentOrder(savedOrder));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.findAllOrders().stream().map(orderPresenter::presentOrder)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Integer id) {
        OrderResponseDTO order = orderPresenter.presentOrder(orderService.findOrderById(id));
        return ResponseEntity.ok(order);

    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@PathVariable Integer id, @Valid @RequestBody OrderDTO orderDTO) {
        Order updatedOrder = orderService.updateOrder(id, orderDTO);
        OrderResponseDTO orderResponseDTO = orderPresenter.presentOrder(updatedOrder);
        return ResponseEntity.ok(orderResponseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<List<OrderDetailsResponseDTO>> getOrderDetails(@PathVariable Integer id) {
        OrderResponseDTO order = orderPresenter.presentOrder(orderService.findOrderById(id));
        List<OrderDetailsResponseDTO> details = order.getOrderDetails();
        return ResponseEntity.ok(details);
    }
}