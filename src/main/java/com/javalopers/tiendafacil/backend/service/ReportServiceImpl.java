package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
import com.javalopers.tiendafacil.backend.dto.OrderDetailsResponseDTO;
import com.javalopers.tiendafacil.backend.dto.OrderResponseDTO;
import com.javalopers.tiendafacil.backend.model.Order;
import com.javalopers.tiendafacil.backend.model.OrderDetails;
import com.javalopers.tiendafacil.backend.repository.CustomerRepository;
import com.javalopers.tiendafacil.backend.repository.OrderRepository;
import com.javalopers.tiendafacil.backend.service.interfaces.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    private LocalDateTime convertToDateTime (Timestamp timestamp){
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }

    @Override
    public List<CustomerDTO> getActiveCustomers() {

        List<Object[]> result = customerRepository.findActiveCustomers();

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Object[] row : result) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId((Integer) row[0]);
            customerDTO.setFirstName((String) row[1]);
            customerDTO.setLastName((String) row[2]);
            customerDTO.setAddress((String) row[3]);
            customerDTO.setMail((String) row[4]);
            customerDTO.setRegDate(convertToDateTime((Timestamp) row[5]));
            customerDTO.setActive(((Long) row[6]) == 1);
            customerDTOList.add(customerDTO);
        }

        return customerDTOList;
    }

    @Override
    public List<OrderResponseDTO> getPendingOrders() {

        List<Order> ordenesPendiente = orderRepository.findPendingOrder();

        return ordenesPendiente.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para convertir de Order a OrderResponseDTO
    public OrderResponseDTO convertToDTO(Order order) {

        List<OrderDetailsResponseDTO> orderDetailsDTO = order.getOrderDetails().stream()
                .map(this::convertOrderDetailsToDTO)
                .collect(Collectors.toList());

        return OrderResponseDTO.builder()
                .orderId(order.getOrderId())
                .customerName(order.getCustomer().getFirstName())
                .orderDate(order.getOrderDate())
                .deliveryDate(order.getDeliveryDate())
                .status(order.getStatus().getStatus())
                .orderDetails(orderDetailsDTO)
                .build();
    }

    // Método para convertir de OrderDetails a OrderDetailsDTO
    private OrderDetailsResponseDTO convertOrderDetailsToDTO(OrderDetails orderDetails) {
        return OrderDetailsResponseDTO.builder()
                .productId(orderDetails.getProduct().getProductId())
                .productName(orderDetails.getProduct().getProductName())
                .productAmount(orderDetails.getProductAmount())
                .productPrice(orderDetails.getProduct().getUnitPrice())
                .build();
    }

}
