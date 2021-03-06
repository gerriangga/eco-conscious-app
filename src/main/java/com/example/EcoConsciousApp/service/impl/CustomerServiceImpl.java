package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.dto.CustomerSearchDTO;
import com.example.EcoConsciousApp.entity.Customer;
import com.example.EcoConsciousApp.exception.DataNotFoundException;
import com.example.EcoConsciousApp.repository.CustomerRepository;
import com.example.EcoConsciousApp.service.CustomerService;
import com.example.EcoConsciousApp.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, String id) {
        validatePresent(id);
        Customer customerById = customerRepository.findById(id).get();
        customerById.setFullName(customer.getFullName());
        customerById.setAddress(customer.getAddress());
        customerById.setPhoneNumber(customer.getPhoneNumber());
        customerById.setEmail(customer.getEmail());
        customerById.setPassword(customer.getPassword());
        return  customerRepository.save(customerById);
    }

    @Override
    public Customer getCustomerById(String id) {
        validatePresent(id);
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(customerSearchDTO);
        return customerRepository.findAll(customerSpecification, pageable);
    }

    @Override
    public void deleteCustomer(String id) {
        validatePresent(id);
        customerRepository.deleteById(id);
    }

    @Override
    public void updateCustomerStatus(String id) {
        validatePresent(id);
        customerRepository.updateCustomerStatus(id);
    }

    @Override
    public Customer storeImageFile(MultipartFile multipartFile, String id) {
        validatePresent(id);
        Customer customerById = customerRepository.findById(id).get();
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        customerById.setPhoto(fileName);

        try {
            customerById.setData(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customerRepository.save(customerById);
    }

    @Override
    public Customer getImageFile(String id) {
        validatePresent(id);
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> getActiveCustomer() {
        return customerRepository.findActiveCustomer();
    }

    @Override
    public List<Customer> getNonActiveCustomer() {
        return customerRepository.findNonActiveCustomer();
    }

    private void validatePresent(String id) {
        if (!customerRepository.findById(id).isPresent()) {
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "customer", id);
            throw new DataNotFoundException(message);
        }
    }
}

