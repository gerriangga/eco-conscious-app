package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository <PurchaseProduct, String> {
}
