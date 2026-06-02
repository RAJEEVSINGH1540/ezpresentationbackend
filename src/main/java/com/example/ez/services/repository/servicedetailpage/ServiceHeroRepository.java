package com.example.ez.services.repository.servicedetailpage;

import com.example.ez.services.entity.servicedetailpage.ServiceHero;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ServiceHeroRepository extends JpaRepository<ServiceHero, Long> {
    Optional<ServiceHero> findByServiceId(String serviceId);
    boolean existsByServiceId(String serviceId);
}