package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.PurchaseProduct;
import com.example.EcoConsciousApp.service.PurchaseProductService;
import com.example.EcoConsciousApp.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiUrlConstant.PURCHASE_PRODUCT)
public class PurchaseProductController {
    @Autowired
    PurchaseProductService purchaseProductService;

    @PostMapping
    public ResponseEntity<ResponseUtils> customerPurchaseProduct(@Valid @RequestBody PurchaseProduct purchaseProduct){
        String message = String.format(ResponseMessage.DATA_INSERTED, "purchase product");
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseUtils);
    }

    @GetMapping("/{id}")
    public PurchaseProduct getPurchaseProduct(@PathVariable  String id){
        return purchaseProductService.getTransactionById(id);
    }

}
