package com.javalopers.tiendafacil.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerDTO {

    private Integer customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String mail;
    private LocalDateTime regDate;
    private Boolean active;
}
