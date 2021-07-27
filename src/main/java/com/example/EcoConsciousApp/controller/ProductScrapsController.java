package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.dto.ResponseData;
import com.example.EcoConsciousApp.dto.SearchData;
import com.example.EcoConsciousApp.entity.ProductScraps;
import com.example.EcoConsciousApp.entity.Supplier;
import com.example.EcoConsciousApp.service.impl.ProductScrapsServiceImpl;
import com.example.EcoConsciousApp.utils.CSVHelper;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.Arrays;

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
    ProductScrapsServiceImpl productScrapsService;

    @PostMapping
    public ResponseEntity<ResponseData<ProductScraps>> saveProductScraps(@Valid @RequestBody ProductScraps productScraps, Errors errors){

        ResponseData<ProductScraps> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(productScrapsService.saveProductScraps(productScraps));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<ProductScraps>> getAllProductScraps(){
        ResponseData<ProductScraps> responseData = new ResponseData<>();
        try{
            List<ProductScraps> productScraps = productScrapsService.getAllProductScraps();
            responseData.setStatus(true);
            responseData.getMessages().add(ResponseMessage.GET_PRODUCT_SCRAPS);
            responseData.setOtherPayload(productScraps);
            return ResponseEntity.ok(responseData);
        }catch(Exception ex){
            responseData.setStatus(false);
            responseData.getMessages().add(ResponseMessage.CANT_GET_PRODUCT_SCRAPS + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @GetMapping("/{productScrapsId}")
    public ProductScraps getProductScrapsById(@PathVariable("productScrapsId") String id){
        return productScrapsService.getProductScrapsById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<ProductScraps>> updateProductScraps(@Valid @RequestBody ProductScraps productScraps, Errors errors){
        ResponseData<ProductScraps> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setStatus(true);
        responseData.setPayload(productScrapsService.saveProductScraps(productScraps));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{productScrapsId}")
    public void deleteProductScrapsById(@PathVariable("productScrapsId") String id){
        productScrapsService.deleteProductScrapsById(id);
    }

    @PostMapping("/{id}")
    public void addSupplierAndProduct(@RequestBody Supplier supplier,@PathVariable("id") String id){
        productScrapsService.addSupplier(supplier, id);
    }

    @GetMapping("/search/category/{id}")
    public List<ProductScraps> getProductScrapsByCategoryId(@PathVariable("id") String id){
        return productScrapsService.findProductScrapsByCategoryId(id);
    }

    @PostMapping("/search/{size}/{page}")
    public Iterable<ProductScraps> findProductPerPage(@RequestBody SearchData searchData,
                                                      @PathVariable("size") int size,
                                                      @PathVariable("page") int page){
        Pageable pageable = PageRequest.of(page, size);
        return productScrapsService.findProductScrapsPerPage(searchData.getSearchKey(), pageable);
    }

    @PostMapping("/report/{format}")
    public String generateReport(@RequestBody SearchData searchData, @PathVariable("format") String format) throws JRException, FileNotFoundException {
        return productScrapsService.exportReport(format, searchData.getSearchKey());
    }

    @PostMapping("/search/{size}/{page}/{sort}")
    public Iterable<ProductScraps> findProductPerPage(@RequestBody SearchData searchData,
                                                      @PathVariable("size") int size,
                                                      @PathVariable("page") int page,
                                                      @PathVariable("sort") String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by("productName").ascending());

        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, size, Sort.by("productName").descending());
        }

        return productScrapsService.findProductScrapsPerPage(searchData.getSearchKey(), pageable);
    }

    @PostMapping("/batch")
    public ResponseEntity<ResponseData<Iterable<ProductScraps>>> saveBatchProductScraps(@RequestBody ProductScraps[] productScraps){
        ResponseData<Iterable<ProductScraps>> responseData = new ResponseData<>();

        responseData.setPayload(productScrapsService.saveBatchProductScraps(Arrays.asList(productScraps))); //casting from array to list
        responseData.setStatus(true);
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseData> uploadFileCSV(@RequestParam("file") MultipartFile multipartFile){
        ResponseData responseData = new ResponseData<>();

        if(!CSVHelper.hasCSVFormat(multipartFile)){
            responseData.setStatus(false);
            responseData.getMessages().add(ResponseMessage.PLEASE_UPLOAD_CSV_FILE + multipartFile.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        try{
            List<ProductScraps> productScraps = productScrapsService.saveCSV(multipartFile);
            responseData.setStatus(true);
            responseData.getMessages().add(ResponseMessage.UPLOAD_SUCCESS + multipartFile.getOriginalFilename());
            responseData.setOtherPayload(productScraps);
            return ResponseEntity.ok(responseData);

        }catch (Exception ex){
            responseData.setStatus(false);
            responseData.getMessages().add(ResponseMessage.CANT_UPLOAD_FILE + multipartFile.getOriginalFilename() + " " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseData);
        }
    }

    @GetMapping("download-image/{productScrapsId}")
    public ResponseEntity<ByteArrayResource> downloadImageFile(@PathVariable String productScrapsId, MultipartFile multipartFile) {
        ProductScraps productScraps = productScrapsService.getImageFile(productScrapsId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename= " + productScraps.getProductScrapsImage())
                .body(new ByteArrayResource(productScraps.getData()));

    }

    @PutMapping("/insert-image")
    public UploadFileResponse uploadImageFile(@RequestParam String id, @RequestParam("imageFile") MultipartFile multipartFile) throws IOException {
        ProductScraps productScraps= productScrapsService.saveImageFile(multipartFile, id);

        String imageFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ApiUrlConstant.USABLE_PRODUCT + "/download-image/")
                .path(productScraps.getId())
                .toUriString();

        return new UploadFileResponse(productScraps.getProductScrapsImage(), imageFileDownloadUri,
                multipartFile.getContentType(), multipartFile.getSize());
    }


}
//    @Autowired
//    ProductScrapsService productService;

//    @PostMapping
//    public ResponseEntity<Response<ProductScraps>> createProduct(@RequestBody ProductScraps productScraps){
//        Response<ProductScraps> response = new Response<ProductScraps>();
//        String message = String.format(ResponseMessage.DATA_INSERTED, "product");
//        response.setMessage(message);
//        response.setTimestamp(new Date());
//        response.setData(productService.saveProductScraps(productScraps));
//
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(response);
//    }
