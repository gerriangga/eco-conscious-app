package com.example.EcoConsciousApp.controller;


import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.entity.ProductScraps;
import com.example.EcoConsciousApp.service.ProductScrapsService;
import com.example.EcoConsciousApp.utils.Response;
import com.example.EcoConsciousApp.utils.UploadFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT_SCRAPS)
public class ProductScrapsController {
    @Autowired
    ProductScrapsService productService;

    @PostMapping
    public ResponseEntity<Response<ProductScraps>> createProduct(@RequestBody ProductScraps productScraps){
        Response<ProductScraps> response = new Response<ProductScraps>();
        String message = String.format(ResponseMessage.DATA_INSERTED, "product");
        response.setMessage(message);
        response.setTimestamp(new Date());
        response.setData(productService.saveProductScraps(productScraps));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{productId}")
    public ProductScraps getProductById(@PathVariable String productId){
        return productService.getProductScrapsById(productId);
    }

    @GetMapping
    public List<ProductScraps> getAllProduct(){
        return productService.getAllProductScraps();
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductScraps productScraps){
        productService.saveProductScraps(productScraps);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam String id) {
        productService.deleteProductScraps(id);
    }

    @GetMapping("download-image/{productScrapsId}")
    public ResponseEntity<ByteArrayResource> downloadImageFile(@PathVariable String productScrapsId, MultipartFile multipartFile) {
        ProductScraps productScraps = productService.getImageFile(productScrapsId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= " + productScraps.getProductScrapsImage())
                .body(new ByteArrayResource(productScraps.getData()));

    }


    @PutMapping("/insert-image")
    public UploadFileResponse uploadImageFile(@RequestParam String id, @RequestParam("imageFile") MultipartFile multipartFile) throws IOException {
        ProductScraps productScraps= productService.saveImageFile(multipartFile, id);

        String imageFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ApiUrlConstant.USABLE_PRODUCT + "/download-image/")
                .path(productScraps.getId())
                .toUriString();

        return new UploadFileResponse(productScraps.getProductScrapsImage(), imageFileDownloadUri,
                multipartFile.getContentType(), multipartFile.getSize());
    }


}


