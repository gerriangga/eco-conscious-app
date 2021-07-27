package com.example.EcoConsciousApp.controller;


import com.example.EcoConsciousApp.constant.ApiUrlConstant;
import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.dto.VendorSearchDTO;
import com.example.EcoConsciousApp.entity.ProductScraps;
import com.example.EcoConsciousApp.entity.Vendor;
import com.example.EcoConsciousApp.service.ReportService;
import com.example.EcoConsciousApp.service.VendorService;
import com.example.EcoConsciousApp.utils.PageResponseWrapper;
import com.example.EcoConsciousApp.utils.Response;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.VENDOR)
public class VendorController {

    @Autowired
    VendorService vendorService;

    @Autowired
    ReportService reportService;

    @PostMapping
    public ResponseEntity<Response<Vendor>> createVendor(@RequestBody Vendor vendor){
        Response<Vendor> response = new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERTED, "vendor");
        response.setMessage(message);
        response.setData(vendorService.saveVendor(vendor));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{vendorId}")
    public Vendor getVendorById(@PathVariable String vendorId){
        return vendorService.getVendorById(vendorId);
    }

    @PutMapping
    public Vendor updateVendor(@RequestBody Vendor vendor){
        return vendorService.saveVendor(vendor);
    }

    @DeleteMapping
    public void deleteVendor(@RequestParam String id){
        vendorService.deleteVendor(id);
    }

    @GetMapping
    public PageResponseWrapper<Vendor> searchVendorPerPage(@RequestParam(name = "vendorName", required = false) String vendorName,
                                                             @RequestParam(name = "vendorAddress", required = false) String vendorAddress,
                                                             @RequestParam(name = "vendorStatus", required=false) Integer status,
                                                             @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(name = "size", defaultValue = "3") Integer sizePerPage,
                                                             @RequestParam(name = "sortBy", defaultValue = "vendorName") String sortBy,
                                                             @RequestParam(name = "direction", defaultValue = "ASC") String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        VendorSearchDTO vendorSearchDTO = new VendorSearchDTO();
        vendorSearchDTO.setSearchVendorName(vendorName);
        vendorSearchDTO.setSearchVendorAddress(vendorAddress);
        vendorSearchDTO.setSearchStatus(status);
        Page<Vendor> vendorPage = vendorService.getVendorPerPage(pageable, vendorSearchDTO);
        return new PageResponseWrapper<Vendor>(vendorPage);
    }

    @GetMapping("/active")
    public List<Vendor> getActiveVendor(){
        return vendorService.getActiveVendor();
    }

    @GetMapping("/not-active")
    public List<Vendor> getNotActiveVendor(@RequestParam(name = "name") String name){
        return vendorService.getNonActiveVendor(name);
    }

    @PutMapping("/active")
    public void changeVendorStatusActive(@RequestParam(name = "id") String id){
        vendorService.updateVendorStatus(id);
    }

    @GetMapping("/report/{format}")
    public String generatedReport(@PathVariable String format,
                                  @RequestParam(name = "vendorName", required = false) String vendorName,
                                  @RequestParam(name = "vendorAddress", required = false) String vendorAddress,
                                  @RequestParam(name = "vendorStatus", required=false) Integer status) throws FileNotFoundException, JRException {
        VendorSearchDTO vendorSearchDTO = new VendorSearchDTO();
        vendorSearchDTO.setSearchVendorName(vendorName);
        vendorSearchDTO.setSearchVendorAddress(vendorAddress);
        vendorSearchDTO.setSearchStatus(status);
        List<Vendor> vendorFilter = vendorService.filterVendor(vendorSearchDTO);
        return reportService.exportReport(format, vendorFilter);
    }


}
