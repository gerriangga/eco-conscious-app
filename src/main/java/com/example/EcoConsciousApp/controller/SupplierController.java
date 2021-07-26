package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.dto.ResponseData;
import com.example.EcoConsciousApp.dto.SearchData;
import com.example.EcoConsciousApp.dto.SupplierDTO;
import com.example.EcoConsciousApp.entity.Supplier;
import com.example.EcoConsciousApp.service.impl.SupplierServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.SUPPLIER)
public class SupplierController {
    @Autowired
    SupplierServiceImpl supplierService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> saveSupplier(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors){

        ResponseData<Supplier> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.saveSupplier(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public List<Supplier> getAllSupplier(){
        return supplierService.getAllSupplier();
    }

    @GetMapping("/{supplierId}")
    public Supplier getSupplierById(@PathVariable("supplierId") String id){
        return supplierService.getSupplierById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> updateUser(@Valid @RequestBody Supplier supplier, Errors errors){
        ResponseData<Supplier> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(supplierService.saveSupplier(supplier));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{supplierId}")
    public void deleteSupplier(@PathVariable("supplierId") String id){
        supplierService.deleteSupplier(id);
    }

    @PostMapping("/search/byemail")
    public List<Supplier> findSupplierByEmail(@RequestBody SearchData searchData){
        return supplierService.findByEmail(searchData.getSearchKey());
    }

}
