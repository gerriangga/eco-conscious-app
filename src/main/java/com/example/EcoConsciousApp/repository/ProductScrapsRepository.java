package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.ProductScraps;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductScrapsRepository extends JpaRepository<ProductScraps, String> {

    @Query("SELECT p FROM ProductScraps p WHERE p.category.id = ?1") //using JPAQL
    public List<ProductScraps> getProductScrapsByCategoryId(String id);

    Page<ProductScraps> findByProductNameContains(String name, Pageable pageable);
    List<ProductScraps> findProductScrapsByProductNameContainingIgnoreCase(String name);
}
