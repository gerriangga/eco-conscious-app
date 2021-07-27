package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.ProductScraps;
import com.example.EcoConsciousApp.entity.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface VendorRepository extends JpaRepository<Vendor, String> {
    Page<Vendor> findAll(Specification<Vendor> vendorSpecification, Pageable pageable);
    List<Vendor> findAll(Specification<Vendor> vendorSpecification);
    public List<Vendor> findVendorByVendorNameContainingIgnoreCase(String VendorName);

    @Query(value = "SELECT * FROM mst_vendor c WHERE c.status = 1", nativeQuery = true)
    List<Vendor> findActiveVendor();

    @Query("SELECT c FROM Vendor c WHERE c.status = 0 and c.vendorName = ?1 ")
    List<Vendor> findNonActiveVendor(String name);

    @Modifying
    @Query("UPDATE Vendor c SET c.status = 1 WHERE  c.vendorId= :id")
    void updateVendorStatus(@Param("id")String id);

    Optional<Vendor> findByVendorEmail(String vendorEmail);

    Boolean existsByVendorEmail(String vendorEmail);

}
