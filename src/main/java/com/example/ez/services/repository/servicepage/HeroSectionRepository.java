package com.example.ez.services.repository.servicepage;

import com.example.ez.services.entity.servicepage.HeroSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface HeroSectionRepository extends JpaRepository<HeroSection, Long> {}