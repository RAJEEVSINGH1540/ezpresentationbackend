// src/main/java/com/cms/repository/ServiceRelatedRepository.java
package com.example.ez.services.repository.servicedetailpage;

import com.example.ez.services.entity.servicedetailpage.ServiceRelated;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceRelatedRepository extends JpaRepository<ServiceRelated, Long> {
    List<ServiceRelated> findByServiceIdOrderBySortOrder(String serviceId);
    void deleteByServiceId(String serviceId);
}