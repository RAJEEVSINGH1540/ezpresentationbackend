package com.example.ez.erpwork.repository;

import com.example.ez.erpwork.entity.ErpService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ErpServiceRepository extends JpaRepository<ErpService, Long> {
    List<ErpService> findAllByOrderByDisplayOrderAsc();
    List<ErpService> findByActiveOrderByDisplayOrderAsc(boolean active);
    Optional<ErpService> findBySlug(String slug);
    boolean existsBySlug(String slug);
}