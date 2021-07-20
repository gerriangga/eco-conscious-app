package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.UsableProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsableProductRepository extends JpaRepository<UsableProduct, String> {
}
