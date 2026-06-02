// src/main/java/com/cms/repository/ServiceStatRepository.java
package com.example.ez.services.repository.servicedetailpage;

import com.example.ez.services.entity.servicedetailpage.ServiceStat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceStatRepository extends JpaRepository<ServiceStat, Long> {
    List<ServiceStat> findByServiceIdOrderBySortOrder(String serviceId);
    void deleteByServiceId(String serviceId);
}