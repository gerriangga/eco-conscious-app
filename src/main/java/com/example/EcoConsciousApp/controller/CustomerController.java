package com.example.EcoConsciousApp.controller;


import com.example.EcoConsciousApp.constant.ApiUrlConstant;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.Customer;
import com.example.EcoConsciousApp.service.CustomerService;
import com.example.EcoConsciousApp.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER)
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<Response<Customer>> createCustomer(@RequestBody Customer customer) {
        Response<Customer> response = new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERTED, "customer");
        response.setMessage(message);
        response.setData(customerService.saveCustomer(customer));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }


}
