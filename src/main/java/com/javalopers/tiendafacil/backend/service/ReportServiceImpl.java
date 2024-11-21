package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
import com.javalopers.tiendafacil.backend.dto.OrderDTO;
import com.javalopers.tiendafacil.backend.dto.OrderDetailsDTO;
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
            customerDTO.setRegDate(convertToDateTime((Timestamp) row[5]));  // Aquí se toma la fecha de registro desde la consulta
            customerDTO.setActive(((Long) row[6]) == 1); // Este es un valor que puedes determinar según tu lógica
            customerDTOList.add(customerDTO);
        }

        return customerDTOList;
    }

    @Override
    public List<OrderDTO> getPendingOrders() {

        // Obtiene las órdenes pendientes del repositorio
        List<Order> ordenesPendiente = orderRepository.findPendingOrder();

        // Convierte cada Order a OrderDTO utilizando el Builder
        return ordenesPendiente.stream()
                .map(this::convertToDTO)  // Llama al método que convierte cada Order en OrderDTO
                .collect(Collectors.toList());  // Recoge todos los DTOs en una lista
    }

    // Método para convertir de Order a OrderDTO utilizando el Builder
    public OrderDTO convertToDTO(Order order) {
        // Convierte los detalles del pedido
        List<OrderDetailsDTO> orderDetailsDTO = order.getOrderDetails().stream()
                .map(this::convertOrderDetailsToDTO)  // Asumiendo que tienes un método para convertir detalles
                .collect(Collectors.toList());

        // Usamos el Builder para crear el OrderDTO
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .customerId(order.getCustomer().getCustomerId())
                .orderDate(order.getOrderDate())
                .deliveryDate(order.getDeliveryDate())
                .statusId(order.getStatus().getStatusId())
                .orderDetails(orderDetailsDTO)
                .build();
    }

    // Método para convertir un detalle de la orden a OrderDetailsDTO (esto es solo un ejemplo)
    private OrderDetailsDTO convertOrderDetailsToDTO(OrderDetails orderDetails) {
        return OrderDetailsDTO.builder()
                .detailsId(orderDetails.getDetailsId())
                .orderId(orderDetails.getOrder().getOrderId())
                .productId(orderDetails.getProduct().getProductId())
                .productAmount(orderDetails.getProductAmount())
                .build();
    }

}
