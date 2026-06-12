package com.example.ez.erpwork.repository;

import com.example.ez.erpwork.entity.ErpFaqItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ErpFaqItemRepository extends JpaRepository<ErpFaqItem, Long> {
    List<ErpFaqItem> findByErpServiceIdOrderByDisplayOrderAsc(Long serviceId);
    void deleteByErpServiceId(Long serviceId);
}