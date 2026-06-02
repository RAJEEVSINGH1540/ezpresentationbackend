package com.example.ez.services.repository.servicepage;

import com.example.ez.services.entity.servicepage.IndustriesSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IndustriesSectionRepository extends JpaRepository<IndustriesSection, Long> {}