package com.example.ez.erpwork.repository;

import com.example.ez.erpwork.entity.ErpProductFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ErpProductFeatureRepository extends JpaRepository<ErpProductFeature, Long> {
    List<ErpProductFeature> findByErpProductIdOrderByDisplayOrderAsc(Long productId);
    void deleteByErpProductId(Long productId);
}