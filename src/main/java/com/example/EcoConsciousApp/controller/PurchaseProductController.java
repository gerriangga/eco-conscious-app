package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.dto.TransactionSearchDTO;
import com.example.EcoConsciousApp.entity.PurchaseProduct;
import com.example.EcoConsciousApp.service.PurchaseProductService;
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
@RequestMapping(ApiUrlConstant.PURCHASE_PRODUCT)
public class PurchaseProductController {
    @Autowired
    PurchaseProductService purchaseProductService;

    @PostMapping
    public ResponseEntity<ResponseUtils<PurchaseProduct>> customerPurchaseProduct(@Valid @RequestBody PurchaseProduct purchaseProduct) {
        String message = String.format(ResponseMessage.DATA_ADDED, "purchase product");
        ResponseUtils<PurchaseProduct> responseUtils = new ResponseUtils<>();
        responseUtils.setTimestamp(new Date());
        responseUtils.setStatusCode(HttpStatus.CREATED.value());
        responseUtils.setMessage(message);
        responseUtils.setData(purchaseProductService.transaction(purchaseProduct));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseUtils);
    }

    @GetMapping("/{id}")
    public PurchaseProduct getPurchaseProduct(@PathVariable String id) {
        return purchaseProductService.getTransactionById(id);
    }

    @GetMapping
    public List<PurchaseProduct> getAllPurchase(@RequestParam(name = "idCustomer") String id) {
        TransactionSearchDTO transactionSearchDTO = new TransactionSearchDTO(id);
        return purchaseProductService.getAllTransaction(transactionSearchDTO);
    }


}
