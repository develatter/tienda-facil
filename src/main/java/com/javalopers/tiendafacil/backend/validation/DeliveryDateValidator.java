package com.javalopers.tiendafacil.backend.validation;

import com.javalopers.tiendafacil.backend.dto.OrderDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DeliveryDateValidator implements ConstraintValidator<ValidDeliveryDate, OrderDTO> {
    @Override
    public boolean isValid(OrderDTO orderDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (orderDTO.getOrderDate() == null || orderDTO.getDeliveryDate() == null) return true;

        return !orderDTO.getDeliveryDate().isBefore(orderDTO.getOrderDate().toLocalDate());
    }
}
