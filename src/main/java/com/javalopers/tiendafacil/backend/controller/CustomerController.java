package com.javalopers.tiendafacil.backend.controller;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
import com.javalopers.tiendafacil.backend.model.Customer;
import com.javalopers.tiendafacil.backend.service.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveCustomer (@RequestBody CustomerDTO customerRequest){

        return customerService.saveCustomer(customerRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getAllCustomers (){

        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById (@PathVariable Integer id){

        return customerService.getCustomer(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer (@PathVariable Integer  id, @RequestBody CustomerDTO customerRequest){

        return customerService.updateCustomer(id,customerRequest);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer (@PathVariable (value = "id") Integer id){

        customerService.deleteCustomer(id);
    }
}
