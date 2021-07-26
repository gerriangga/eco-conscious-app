package com.example.EcoConsciousApp.service.impl;

import com.example.EcoConsciousApp.entity.Supplier;
import com.example.EcoConsciousApp.repository.SupplierRepository;
import com.example.EcoConsciousApp.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier getSupplierById(String id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(!supplier.isPresent())
            return null;
        return supplierRepository.findById(id).get();
    }

    @Override
    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public void deleteSupplier(String id) {
        Supplier supplier = getSupplierById(id);
        supplier.setIsDeleted(true);
        supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> findByEmail(String email) {
        return supplierRepository.findByEmailContainsOrderByEmailAsc(email);
    }
}
