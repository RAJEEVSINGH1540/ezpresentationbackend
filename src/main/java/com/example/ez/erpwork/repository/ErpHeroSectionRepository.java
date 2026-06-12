package com.example.ez.erpwork.repository;

import com.example.ez.erpwork.entity.ErpHeroSection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ErpHeroSectionRepository extends JpaRepository<ErpHeroSection, Long> {
    Optional<ErpHeroSection> findByErpServiceId(Long serviceId);
}