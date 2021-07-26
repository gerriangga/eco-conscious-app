package com.example.EcoConsciousApp.controller;


import com.example.EcoConsciousApp.constant.ApiUrlConstant;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.dto.CustomerSearchDTO;
import com.example.EcoConsciousApp.entity.Customer;
import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.service.CustomerService;
import com.example.EcoConsciousApp.service.ReportCustomerService;
import com.example.EcoConsciousApp.utils.PageResponseWrapperUtils;
import com.example.EcoConsciousApp.utils.ResponseUtils;
import com.example.EcoConsciousApp.utils.UploadFileResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    @GetMapping("download-image/{customerId}")
    public ResponseEntity<Resource> downloadImageFile(@PathVariable String customerId, MultipartFile multipartFile) {
        Customer customer = customerService.getImageFile(customerId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + customer.getProfileImage())
                .body(new ByteArrayResource(customer.getData()));


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

    @PutMapping("/active")
    public void changeCustomerStatusActive(@RequestParam(name = "id") String id) {
        customerService.updateCustomerStatus(id);
    }

    @PutMapping("/insert-image")
    public UploadFileResponse uploadImageFile(@RequestParam String id, @RequestParam("imageFile") MultipartFile multipartFile) throws IOException {

        Customer customer = customerService.storeImageFile(multipartFile, id);

        String imageFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ApiUrlConstant.CUSTOMER + "/download-image/")
                .path(customer.getId())
                .toUriString();

        return new UploadFileResponse(customer.getProfileImage(), imageFileDownloadUri, multipartFile.getContentType(), multipartFile.getSize());
    }
}
