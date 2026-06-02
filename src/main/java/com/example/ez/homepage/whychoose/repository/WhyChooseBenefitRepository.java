package com.example.ez.homepage.whychoose.repository;

import com.example.ez.homepage.whychoose.entity.WhyChooseBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhyChooseBenefitRepository extends JpaRepository<WhyChooseBenefit, Long> {
    List<WhyChooseBenefit> findAllByOrderBySortOrderAsc();
}