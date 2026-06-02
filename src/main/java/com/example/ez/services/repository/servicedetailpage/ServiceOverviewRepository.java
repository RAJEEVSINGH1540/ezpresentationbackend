// src/main/java/com/cms/repository/ServiceOverviewRepository.java
package com.example.ez.services.repository.servicedetailpage;

import com.example.ez.services.entity.servicedetailpage.ServiceOverview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ServiceOverviewRepository extends JpaRepository<ServiceOverview, Long> {
    Optional<ServiceOverview> findByServiceId(String serviceId);
}