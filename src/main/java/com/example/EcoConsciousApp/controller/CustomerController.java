package com.example.EcoConsciousApp.controller;


import com.example.EcoConsciousApp.constant.ApiUrlConstant;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.dto.CustomerSearchDTO;
import com.example.EcoConsciousApp.entity.Customer;
import com.example.EcoConsciousApp.service.CustomerService;
import com.example.EcoConsciousApp.service.ReportCustomerService;
import com.example.EcoConsciousApp.utils.PageResponseWrapperUtils;
import com.example.EcoConsciousApp.utils.ResponseUtils;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.Date;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER)
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ReportCustomerService reportCustomerService;

    @PostMapping
    public ResponseEntity<ResponseUtils> createCustomer(@Valid @RequestBody Customer customer) {
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setTimestamp(new Date());
        responseUtils.setStatusCode(HttpStatus.CREATED.value());
        String message = String.format(ResponseMessage.DATA_INSERTED, "customer");
        responseUtils.setMessage(message);
        customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseUtils);
    }

    @GetMapping
    public PageResponseWrapperUtils<Customer> searchCustomerPerPage(@RequestParam(name = "firstName", required = false) String firstName,
                                                                    @RequestParam(name = "lastName", required = false) String lastName,
                                                                    @RequestParam(name = "email", required = false) String email,
                                                                    @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                    @RequestParam(name = "size", defaultValue = "3") Integer sizePerPage,
                                                                    @RequestParam(name = "sortBy", defaultValue = "firstName") String sortBy,
                                                                    @RequestParam(name = "direction", defaultValue = "ASC") String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        CustomerSearchDTO customerSearchDTO = new CustomerSearchDTO(firstName, lastName, email);
        Page<Customer> customerPage = customerService.getCustomerPerPage(pageable, customerSearchDTO);
        return new PageResponseWrapperUtils<Customer>(customerPage);
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return reportCustomerService.exportReport(format);
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable String customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<ResponseUtils> updateCustomer(@PathVariable String customerId, @Valid @RequestBody Customer customer) {
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setTimestamp(new Date());
        responseUtils.setStatusCode(HttpStatus.OK.value());
        String message = String.format(ResponseMessage.DATA_UPDATED, "customer", customerId);
        responseUtils.setMessage(message);
        customerService.updateCustomer(customer, customerId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseUtils);
    }

    @DeleteMapping
    public ResponseEntity<ResponseUtils> deleteCustomer(@RequestParam String id) {
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setTimestamp(new Date());
        responseUtils.setStatusCode(HttpStatus.OK.value());
        String message = String.format(ResponseMessage.DATA_DELETED, "customer");
        responseUtils.setMessage(message);
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseUtils);
    }

}
