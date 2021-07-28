package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.PurchaseProduct;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseProductRepository extends JpaRepository <PurchaseProduct, String> {
    List<PurchaseProduct> findAll(Specification<PurchaseProduct> purchaseProductSpecification);
}
