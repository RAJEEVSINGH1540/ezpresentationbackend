// src/main/java/com/cms/repository/ServiceBenefitRepository.java
package com.example.ez.services.repository.servicedetailpage;

import com.example.ez.services.entity.servicedetailpage.ServiceBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceBenefitRepository extends JpaRepository<ServiceBenefit, Long> {
    List<ServiceBenefit> findByServiceIdOrderBySortOrder(String serviceId);
    void deleteByServiceId(String serviceId);
}