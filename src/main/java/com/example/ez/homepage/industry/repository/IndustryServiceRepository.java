package com.example.ez.homepage.industry.repository;

import com.example.ez.homepage.industry.entity.IndustryService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndustryServiceRepository extends JpaRepository<IndustryService, Long> {
    List<IndustryService> findAllByOrderBySortOrderAsc();
    List<IndustryService> findByActiveTrueOrderBySortOrderAsc();
}