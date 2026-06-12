package com.example.ez.erpwork.repository;

import com.example.ez.erpwork.entity.ErpFeatureTab;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ErpFeatureTabRepository extends JpaRepository<ErpFeatureTab, Long> {
    List<ErpFeatureTab> findByErpServiceIdOrderByDisplayOrderAsc(Long serviceId);
    void deleteByErpServiceId(Long serviceId);
}