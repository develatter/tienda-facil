package com.javalopers.tiendafacil.backend.service.interfaces;
import com.javalopers.tiendafacil.backend.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO saveCustomer (CustomerDTO customerRequest);

    List<CustomerDTO> getAllCustomer ();

    CustomerDTO getCustomer (Integer id);

    CustomerDTO updateCustomer (Integer id, CustomerDTO customerRequest);

    void deleteCustomer (Integer id);
}
