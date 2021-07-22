package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.service.UsableProductService;
import com.example.EcoConsciousApp.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.USABLE_PRODUCT)
public class UsableProductController {
    @Autowired
    UsableProductService usableProductService;

    @PostMapping
    public ResponseEntity<ResponseUtils> createUsableProduct(@Valid @RequestBody UsableProduct usableProduct) {
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setTimestamp(new Date());
        responseUtils.setStatusCode(HttpStatus.CREATED.value());
        String message = String.format(ResponseMessage.DATA_INSERTED, "usable product");
        responseUtils.setMessage(message);
        usableProductService.saveUsableProduct(usableProduct);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseUtils);
    }

    @GetMapping
    public List<UsableProduct> getAllUsableProduct() {
        return usableProductService.getAllUsableProduct();
    }

    @GetMapping("/{usableProductId}")
    public UsableProduct getUsableProductById(@PathVariable String usableProductId) {
        return usableProductService.getUsableProductById(usableProductId);
    }

    @PutMapping("/{usableProductId}")
    public ResponseEntity<ResponseUtils> updateUsableProduct(@PathVariable String usableProductId, @Valid @RequestBody UsableProduct usableProduct) {
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setTimestamp(new Date());
        responseUtils.setStatusCode(HttpStatus.OK.value());
        String message = String.format(ResponseMessage.DATA_UPDATED, "usable product", usableProductId);
        responseUtils.setMessage(message);
        usableProductService.updateUsableProduct(usableProduct, usableProductId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseUtils);

    }

    @DeleteMapping
    public ResponseEntity<ResponseUtils> deleteUsableProduct(@RequestParam String id) {
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setTimestamp(new Date());
        responseUtils.setStatusCode(HttpStatus.OK.value());
        String message = String.format(ResponseMessage.DATA_DELETED, "usable product");
        responseUtils.setMessage(message);
        usableProductService.deleteUsableProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseUtils);
    }
}
