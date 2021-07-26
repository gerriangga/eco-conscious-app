package com.example.EcoConsciousApp.service;

import com.example.EcoConsciousApp.entity.Supplier;

import java.util.List;

public interface SupplierService {
    public Supplier saveSupplier(Supplier supplier);
    public Supplier getSupplierById(String id);
    public List<Supplier> getAllSupplier();
    public void deleteSupplier(String id);
    public Iterable<Supplier> findByEmail(String email);
}
