package com.javalopers.tiendafacil.backend.service.interfaces;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
import com.javalopers.tiendafacil.backend.model.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDTO saveCustomer (Customer customer);

    List<CustomerDTO> getAllCustomer ();

    CustomerDTO getCustomer (Integer id);

    CustomerDTO updateCustomer (Integer id, Customer customer);

    void deleteCustomer (Integer id);
}
