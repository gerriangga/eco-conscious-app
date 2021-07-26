package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords
    //Derived Query Methods
    List<Supplier> findByEmailContainsOrderByEmailAsc(String email);
}
