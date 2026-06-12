package com.example.ez.erpwork.repository;

import com.example.ez.erpwork.entity.ErpCustomerSupportSection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ErpCustomerSupportRepository extends JpaRepository<ErpCustomerSupportSection, Long> {
    Optional<ErpCustomerSupportSection> findByErpServiceId(Long serviceId);
}