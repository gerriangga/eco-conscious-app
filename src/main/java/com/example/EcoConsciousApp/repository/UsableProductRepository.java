package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.UsableProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsableProductRepository extends JpaRepository<UsableProduct, String> {
    Page<UsableProduct> findAll(Specification<UsableProduct> usableProductSpecification, Pageable pageable);
}
