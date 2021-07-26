package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.dto.CustomerSearchDTO;
import com.example.EcoConsciousApp.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {
    public Customer saveCustomer(Customer customer);

    public Customer updateCustomer(Customer customer, String id);

    public Customer getCustomerById(String id);

    public List<Customer> getAllCustomer();

    public Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO);

    public void deleteCustomer(String id);

    public void updateCustomerStatus(String id);

    public Customer storeImageFile(MultipartFile multipartFile, String id);

    public Customer getImageFile(String id);
}
