package com.example.EcoConsciousApp.controller;

import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.dto.UsableProductSearchDTO;
import com.example.EcoConsciousApp.entity.UsableProduct;
import com.example.EcoConsciousApp.service.ReportUsableProductService;
import com.example.EcoConsciousApp.service.UsableProductService;
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
@RequestMapping(ApiUrlConstant.USABLE_PRODUCT)
public class UsableProductController {
    @Autowired
    UsableProductService usableProductService;

    @Autowired
    ReportUsableProductService reportUsableProductService;

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
    public PageResponseWrapperUtils<UsableProduct> searchUsableProductPerPage(@RequestParam(name = "usableProductName", required = false) String usableProductName,
                                                                              @RequestParam(name = "usableProductDescription", required = false) String usableProductDescription,
                                                                              @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                              @RequestParam(name = "size", defaultValue = "3") Integer sizePerPage,
                                                                              @RequestParam(name = "sortBy", defaultValue = "usableProductName") String sortBy,
                                                                              @RequestParam(name = "direction", defaultValue = "ASC") String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        UsableProductSearchDTO usableProductSearchDTO = new UsableProductSearchDTO(usableProductName, usableProductDescription);
        Page<UsableProduct> usableProductPage = usableProductService.getUsableProductPerPage(pageable, usableProductSearchDTO);
        return new PageResponseWrapperUtils<UsableProduct>(usableProductPage);
    }

    @GetMapping("/report/{format}")
    public String generateReportUsableProduct(@PathVariable String format) throws FileNotFoundException, JRException {
        return reportUsableProductService.exportReport(format);
    }

    @GetMapping("/{usableProductId}")
    public UsableProduct getUsableProductById(@PathVariable String usableProductId) {
        return usableProductService.getUsableProductById(usableProductId);
    }

    @GetMapping("download-image/{usableProductId}")
    public ResponseEntity<Resource> downloadImageFile(@PathVariable String usableProductId, MultipartFile multipartFile) {
        UsableProduct usableProduct = usableProductService.getImageFile(usableProductId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + usableProduct.getUsableProductImage())
                .body(new ByteArrayResource(usableProduct.getData()));


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

    @PutMapping("/insert-image")
    public UploadFileResponse uploadImageFile(@RequestParam String id, @RequestParam("imageFile") MultipartFile multipartFile) throws IOException {

        UsableProduct usableProduct = usableProductService.storeImageFile(multipartFile, id);

        String imageFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ApiUrlConstant.USABLE_PRODUCT + "/download-image/")
                .path(usableProduct.getId())
                .toUriString();

        return new UploadFileResponse(usableProduct.getUsableProductImage(), imageFileDownloadUri, multipartFile.getContentType(), multipartFile.getSize());
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
