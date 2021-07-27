package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.constant.ResponseMessage;
import com.example.EcoConsciousApp.dto.VendorSearchDTO;
import com.example.EcoConsciousApp.entity.Vendor;
import com.example.EcoConsciousApp.exception.DataNotFoundException;
import com.example.EcoConsciousApp.repository.ProductScrapsRepository;
import com.example.EcoConsciousApp.repository.VendorRepository;
import com.example.EcoConsciousApp.service.ProductScrapsService;
import com.example.EcoConsciousApp.service.VendorService;
import com.example.EcoConsciousApp.specification.VendorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServicelmpl implements VendorService {
    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    ProductScrapsRepository productRepository;

    @Autowired
    ProductScrapsService productService;

    @Override
    public Vendor saveVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor getVendorById(String id) {
        validatePresent(id);
        return vendorRepository.findById(id).get();
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public void deleteVendor(String id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public Page<Vendor> getVendorPerPage(Pageable pageable, VendorSearchDTO vendorSearchDTO) {
        Specification<Vendor> vendorSpecification = VendorSpecification.getSpecification(vendorSearchDTO);
        return vendorRepository.findAll(vendorSpecification, pageable);
    }

    @Override
    public List<Vendor> getVendorByName(String name) {
        return vendorRepository.findVendorByVendorNameContainingIgnoreCase(name);
    }

    @Override
    public List<Vendor> getActiveVendor() {
        return vendorRepository.findActiveVendor();
    }

    @Override
    public List<Vendor> getNonActiveVendor(String name) {
        return vendorRepository.findNonActiveVendor(name);
    }

    @Override
    public void updateVendorStatus(String id) {
        vendorRepository.updateVendorStatus(id);
    }

    @Override
    public List<Vendor> filterVendor(VendorSearchDTO vendorSearchDTO) {
        Specification<Vendor> vendorSpecification = VendorSpecification.getSpecification(vendorSearchDTO);
        return vendorRepository.findAll(vendorSpecification);
    }

    private void validatePresent(String id) {
        if (!vendorRepository.findById(id).isPresent()){
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "vendor", id);
            throw new DataNotFoundException(message);
        }

    }

    public Boolean vendorEmailExist(String vendorEmail) {
        return vendorRepository.existsByVendorEmail(vendorEmail);
    }


}
