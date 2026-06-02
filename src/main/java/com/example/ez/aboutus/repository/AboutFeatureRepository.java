package com.example.ez.aboutus.repository;

import com.example.ez.aboutus.entity.AboutFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AboutFeatureRepository extends JpaRepository<AboutFeature, Long> {
    List<AboutFeature> findByAboutPageIdOrderBySortOrderAsc(Long aboutPageId);
    List<AboutFeature> findByAboutPageIdAndSectionTypeOrderBySortOrderAsc(Long aboutPageId, String sectionType);
    void deleteByAboutPageId(Long aboutPageId);
}