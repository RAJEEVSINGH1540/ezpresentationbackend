package com.example.ez.erpwork.repository;

import com.example.ez.erpwork.entity.ErpFeatureTabItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ErpFeatureTabItemRepository extends JpaRepository<ErpFeatureTabItem, Long> {
    List<ErpFeatureTabItem> findByFeatureTabIdOrderByDisplayOrderAsc(Long tabId);
    void deleteByFeatureTabId(Long tabId);
}