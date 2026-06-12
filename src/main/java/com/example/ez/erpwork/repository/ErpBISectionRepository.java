package com.example.ez.erpwork.repository;

import com.example.ez.erpwork.entity.ErpBusinessIntelligenceSection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ErpBISectionRepository extends JpaRepository<ErpBusinessIntelligenceSection, Long> {
    Optional<ErpBusinessIntelligenceSection> findByErpServiceId(Long serviceId);
}