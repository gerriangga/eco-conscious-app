package com.example.EcoConsciousApp.repository;

import com.example.EcoConsciousApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String username);
    Boolean existsByUserName(String username);
}
