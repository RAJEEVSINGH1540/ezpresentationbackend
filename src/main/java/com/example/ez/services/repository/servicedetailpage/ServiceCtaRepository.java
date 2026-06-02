// src/main/java/com/cms/repository/ServiceCtaRepository.java
package com.example.ez.services.repository.servicedetailpage;

import com.example.ez.services.entity.servicedetailpage.ServiceCta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ServiceCtaRepository extends JpaRepository<ServiceCta, Long> {
    Optional<ServiceCta> findByServiceId(String serviceId);
}