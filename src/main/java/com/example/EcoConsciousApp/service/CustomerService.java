package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.entity.Customer;

import java.util.List;

public interface CustomerService {
    public Customer saveCustomer(Customer customer);
    public Customer getCustomerById(String id);
    public List<Customer> getAllCustomer();
    public void deleteCustomer(String id);
}
