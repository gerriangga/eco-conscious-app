package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.ProductScraps;
import com.example.EcoConsciousApp.entity.VendorProductScrapsPurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorProductScrapsPurchaseDetailRepository extends JpaRepository<VendorProductScrapsPurchaseDetail, String> {
}
