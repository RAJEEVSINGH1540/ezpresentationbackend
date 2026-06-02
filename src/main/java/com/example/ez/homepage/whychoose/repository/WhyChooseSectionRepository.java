package com.example.ez.homepage.whychoose.repository;

import com.example.ez.homepage.whychoose.entity.WhyChooseSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhyChooseSectionRepository extends JpaRepository<WhyChooseSection, Long> {}