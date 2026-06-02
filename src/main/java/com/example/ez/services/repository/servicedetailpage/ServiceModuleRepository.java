// src/main/java/com/cms/repository/ServiceModuleRepository.java
package com.example.ez.services.repository.servicedetailpage;

import com.example.ez.services.entity.servicedetailpage.ServiceModule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceModuleRepository extends JpaRepository<ServiceModule, Long> {
    List<ServiceModule> findByServiceIdOrderBySortOrder(String serviceId);
    void deleteByServiceId(String serviceId);
}