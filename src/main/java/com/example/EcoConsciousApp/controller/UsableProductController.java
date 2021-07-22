package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.dto.UsableProductSearchDTO;
import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.service.UsableProductService;
import com.example.EcoConsciousApp.utils.PageResponseWrapperUtils;
import com.example.EcoConsciousApp.utils.ResponseUtils;
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

//    @GetMapping
//    public List<UsableProduct> getAllUsableProduct() {
//        return usableProductService.getAllUsableProduct();
//    }

    @GetMapping
    public PageResponseWrapperUtils<UsableProduct> searchUsableProductPerPage(@RequestParam(name = "usableProductName", required = false) String usableProductName,
                                                                    @RequestParam(name = "usableProductDescription", required = false) String usableProductDescription,
                                                                    @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                    @RequestParam(name = "size", defaultValue = "3") Integer sizePerPage,
                                                                    @RequestParam(name = "sortBy", defaultValue = "usableProductName") String sortBy,
                                                                    @RequestParam(name = "direction", defaultValue = "ASC") String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        UsableProductSearchDTO usableProductSearchDTO = new UsableProductSearchDTO(usableProductName, usableProductDescription);
        Page<UsableProduct> usableProductPage = usableProductService.getUsableProductPerPage(pageable, usableProductSearchDTO);
        return new PageResponseWrapperUtils<UsableProduct>(usableProductPage);
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
