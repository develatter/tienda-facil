package com.javalopers.tiendafacil.backend.service;

import com.javalopers.tiendafacil.backend.dto.CustomerDTO;
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
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public CustomerDTO saveCustomer(Customer customer) {

        if(customerRepository.existsByMail(customer.getMail())){
            throw new IllegalArgumentException
                    ("Customer with email " + customer.getMail() + " already exists.");
        }
        if(customer.getRegDate() == null){
            customer.setRegDate(LocalDateTime.now());
        }

        customerRepository.save(customer);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setMail(customer.getMail());
        customerDTO.setRegDate(customer.getRegDate());
        customerDTO.setActive(customer.getActive());

        return customerDTO;

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
                orElseThrow(()-> new NoSuchElementException("This Customer does not exist in our System"));

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
    public CustomerDTO updateCustomer(Integer id, Customer customer) {

        Customer existingCustomer = customerRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("This Customer does not exist in our System"));

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());

        // Corroborando único usuario por Mail
        if (!existingCustomer.getMail().equals(customer.getMail())) {
            if (customerRepository.existsByMail(customer.getMail())) {
                throw new IllegalArgumentException("This Mail already exist in our System");
            }
        }

        existingCustomer.setMail(customer.getMail());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setActive(customer.getActive());

        customerRepository.save(existingCustomer);

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(existingCustomer.getCustomerId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setMail(customer.getMail());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setRegDate(existingCustomer.getRegDate());
        customerDTO.setActive(customer.getActive());

        return customerDTO;
    }

    @Override
    public void deleteCustomer(Integer id) {

         customerRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("This Customer does not exist in our System"));

         customerRepository.deleteById(id);
    }
}
