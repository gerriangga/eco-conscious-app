package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.VendorProductScrapsPurchaseDetail;
import com.example.EcoConsciousApp.entity.VendorUsableProductRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorUsableProductRegistrationRepository extends JpaRepository<VendorUsableProductRegistration, String> {
}
