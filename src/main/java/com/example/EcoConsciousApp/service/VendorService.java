package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.dto.ProductScrapsSearchDTO;
import com.example.EcoConsciousApp.dto.VendorSearchDTO;
import com.example.EcoConsciousApp.entity.ProductScraps;
import com.example.EcoConsciousApp.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendorService {
    public Vendor saveVendor(Vendor vendor);
    public Vendor getVendorById(String id);
    public List<Vendor> getAllVendors();
    public void deleteVendor(String id);
    public Page<Vendor> getVendorPerPage(Pageable pageable, VendorSearchDTO vendorSearchDTO);
    public List<Vendor> getVendorByName(String name);
    public List<Vendor> getActiveVendor();
    public List<Vendor> getNonActiveVendor(String name);
    public void updateVendorStatus(String id);
    public List<Vendor> filterVendor(VendorSearchDTO vendorSearchDTO);

}
