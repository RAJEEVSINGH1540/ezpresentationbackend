// src/main/java/com/cms/repository/ServiceFeatureRepository.java
package com.example.ez.services.repository.servicedetailpage;

import com.example.ez.services.entity.servicedetailpage.ServiceFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceFeatureRepository extends JpaRepository<ServiceFeature, Long> {
    List<ServiceFeature> findByServiceIdOrderBySortOrder(String serviceId);
    void deleteByServiceId(String serviceId);
}