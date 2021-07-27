package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.ProductScraps;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface ProductScrapsRepository extends JpaRepository<ProductScraps, String> {
    Page<ProductScraps> findAll(Specification<ProductScraps> productScrapsSpecification, Pageable pageable);
    public List<ProductScraps> findProductScrapsByProductNameContainingIgnoreCase(String ProductName);
}
