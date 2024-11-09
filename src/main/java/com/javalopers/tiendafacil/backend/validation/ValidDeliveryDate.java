package com.javalopers.tiendafacil.backend.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DeliveryDateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDeliveryDate {
    String message() default "La fecha de entrega no puede ser anterior a la del pedido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
