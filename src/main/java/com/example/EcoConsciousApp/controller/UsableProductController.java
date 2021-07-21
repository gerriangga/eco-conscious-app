package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.service.UsableProductService;
import com.example.EcoConsciousApp.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.USABLE_PRODUCT)
public class UsableProductController {
    @Autowired
    UsableProductService usableProductService;

    @PostMapping
    public ResponseEntity<Response<UsableProduct>> createUsableProduct(@Valid @RequestBody UsableProduct usableProduct) {
        Response<UsableProduct> response = new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERTED, "usable product");
        response.setMessage(message);
        response.setData(usableProductService.saveUsableProduct(usableProduct));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping
    public List<UsableProduct> getAllUsableProduct() {
        return usableProductService.getAllUsableProduct();
    }

    @GetMapping("/{usableProductId}")
    public UsableProduct getUsableProductById(@PathVariable String usableProductId) {
        return usableProductService.getUsableProductById(usableProductId);
    }

    @PutMapping
    public UsableProduct updateUsableProduct(@RequestBody UsableProduct usableProduct) {
        return usableProductService.saveUsableProduct(usableProduct);
    }

    @DeleteMapping
    public ResponseEntity<Response<UsableProduct>> deleteUsableProduct(@RequestParam String id) {
        Response<UsableProduct> response = new Response<>();
        String message = String.format("Deleted");
        response.setMessage(message);
        usableProductService.deleteUsableProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
