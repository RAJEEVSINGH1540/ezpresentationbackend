// src/main/java/com/cms/repository/ServiceTechSpecRepository.java
package com.example.ez.services.repository.servicedetailpage;

import com.example.ez.services.entity.servicedetailpage.ServiceTechSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceTechSpecRepository extends JpaRepository<ServiceTechSpec, Long> {
    List<ServiceTechSpec> findByServiceIdOrderBySortOrder(String serviceId);
    void deleteByServiceId(String serviceId);
}