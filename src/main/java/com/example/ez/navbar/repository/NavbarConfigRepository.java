// src/main/java/com/example/ez/homepage/navbar/NavbarConfigRepository.java
package com.example.ez.navbar.repository;

import com.example.ez.navbar.entity.NavbarConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NavbarConfigRepository extends JpaRepository<NavbarConfig, Long> {
    Optional<NavbarConfig> findFirstByActiveTrue();
}