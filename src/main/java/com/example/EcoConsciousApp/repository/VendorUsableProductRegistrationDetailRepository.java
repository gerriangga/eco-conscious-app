package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.VendorUsableProductRegistration;
import com.example.EcoConsciousApp.entity.VendorUsableProductRegistrationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorUsableProductRegistrationDetailRepository extends JpaRepository<VendorUsableProductRegistrationDetail, String> {
}
