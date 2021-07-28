package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Page<Customer> findAll(Specification<Customer> customerSpecification, Pageable pageable);

    @Query(value = "SELECT * FROM mst_customer c WHERE c.status = 1", nativeQuery = true)
    List<Customer> findActiveCustomer();

    @Query(value = "SELECT * FROM mst_customer c WHERE c.status = 0", nativeQuery = true)
    List<Customer> findNonActiveCustomer();

    @Modifying
    @Query("UPDATE Customer c SET c.status = 1 WHERE  c.id = :id")
    void updateCustomerStatus(@Param("id") String id);

    Customer findByPhoneNumber(String phoneNumber);

    Customer findByEmail(String email);

}
