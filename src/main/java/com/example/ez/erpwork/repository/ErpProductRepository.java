package com.example.ez.erpwork.repository;

import com.example.ez.erpwork.entity.ErpProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ErpProductRepository extends JpaRepository<ErpProduct, Long> {
    List<ErpProduct> findByErpServiceIdOrderByDisplayOrderAsc(Long serviceId);
    void deleteByErpServiceId(Long serviceId);
}