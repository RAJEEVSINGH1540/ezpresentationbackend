// src/main/java/com/example/ez/cms/hero/repository/HeroSectionRepository.java
package com.example.ez.homepage.HeroSection.repository;

import com.example.ez.homepage.HeroSection.entity.HomeHeroSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeHeroSectionRepository extends JpaRepository<HomeHeroSection, Long> {}