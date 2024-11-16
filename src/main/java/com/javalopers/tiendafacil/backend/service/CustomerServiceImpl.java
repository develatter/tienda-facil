package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
import com.javalopers.tiendafacil.backend.exception.CustomerAlreadyExistsException;
import com.javalopers.tiendafacil.backend.exception.CustomerDoesNotExistsException;
import com.javalopers.tiendafacil.backend.model.Customer;
import com.javalopers.tiendafacil.backend.repository.CustomerRepository;
import com.javalopers.tiendafacil.backend.service.interfaces.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerRequest) {

        Customer newCustomer = new Customer();
        newCustomer.setCustomerId(customerRequest.getCustomerId());
        newCustomer.setFirstName(customerRequest.getFirstName());
        newCustomer.setLastName(customerRequest.getLastName());
        newCustomer.setAddress(customerRequest.getAddress());
        newCustomer.setMail(customerRequest.getMail());

        if(customerRepository.existsByMail(newCustomer.getMail())){
            throw new CustomerAlreadyExistsException
                    ("Customer with email " + newCustomer.getMail() + " already exists.");
        }
        if(customerRequest.getRegDate() == null){
            customerRequest.setRegDate(LocalDateTime.now());
        }

        newCustomer.setRegDate(customerRequest.getRegDate());
        newCustomer.setActive(customerRequest.getActive());


        customerRepository.save(newCustomer);

        CustomerDTO customerResponse = new CustomerDTO();
        customerResponse.setCustomerId(newCustomer.getCustomerId());
        customerResponse.setFirstName(newCustomer.getFirstName());
        customerResponse.setLastName(newCustomer.getLastName());
        customerResponse.setAddress(newCustomer.getAddress());
        customerResponse.setMail(newCustomer.getMail());
        customerResponse.setRegDate(newCustomer.getRegDate());
        customerResponse.setActive(newCustomer.getActive());

        return customerResponse;

    }

    @Override
    public List<CustomerDTO> getAllCustomer() {

        List <Customer> customerList = customerRepository.findAll();

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for(Customer customer : customerList){

            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setFirstName(customer.getFirstName());
            customerDTO.setLastName(customer.getLastName());
            customerDTO.setAddress(customer.getAddress());
            customerDTO.setMail(customer.getMail());
            customerDTO.setRegDate(customer.getRegDate());
            customerDTO.setActive(customer.getActive());

            customerDTOList.add(customerDTO);

        }

        return customerDTOList;
    }

    @Override
    public CustomerDTO getCustomer(Integer id) {

        Customer existingCustomer = customerRepository.findById(id).
                orElseThrow(()-> new CustomerDoesNotExistsException("El cliente no existe en nuestro sistema"));

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(existingCustomer.getCustomerId());
        customerDTO.setFirstName(existingCustomer.getFirstName());
        customerDTO.setLastName(existingCustomer.getLastName());
        customerDTO.setMail(existingCustomer.getMail());
        customerDTO.setAddress(existingCustomer.getAddress());
        customerDTO.setRegDate(existingCustomer.getRegDate());
        customerDTO.setActive(existingCustomer.getActive());

        return customerDTO;
    }

    @Transactional
    @Override
    public CustomerDTO updateCustomer(Integer id, CustomerDTO customerRequest) {

        Customer existingCustomer = customerRepository.findById(id).
                orElseThrow(()-> new CustomerDoesNotExistsException("El cliente no existe en nuestro sistema"));

        existingCustomer.setFirstName(customerRequest.getFirstName());
        existingCustomer.setLastName(customerRequest.getLastName());

        // Corroborando único usuario por Mail
        if (!existingCustomer.getMail().equals(customerRequest.getMail()) && customerRepository.existsByMail(customerRequest.getMail())) {
            throw new IllegalArgumentException("This Mail already exists in our system");
        }

        existingCustomer.setMail(customerRequest.getMail());
        existingCustomer.setAddress(customerRequest.getAddress());
        existingCustomer.setActive(customerRequest.getActive());

        customerRepository.save(existingCustomer);

        CustomerDTO customerResponse = new CustomerDTO();
        customerResponse.setCustomerId(existingCustomer.getCustomerId());
        customerResponse.setFirstName(existingCustomer.getFirstName());
        customerResponse.setLastName(existingCustomer.getLastName());
        customerResponse.setMail(existingCustomer.getMail());
        customerResponse.setAddress(existingCustomer.getAddress());
        customerResponse.setRegDate(existingCustomer.getRegDate());
        customerResponse.setActive(existingCustomer.getActive());

        return customerResponse;
    }

    @Override
    public void deleteCustomer(Integer id) {

         customerRepository.findById(id).
                orElseThrow(()->  new CustomerDoesNotExistsException("El cliente no existe en nuestro sistema"));

         customerRepository.deleteById(id);
    }
}
