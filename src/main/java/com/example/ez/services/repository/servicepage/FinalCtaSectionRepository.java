package com.example.ez.services.repository.servicepage;

import com.example.ez.services.entity.servicepage.FinalCtaSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalCtaSectionRepository extends JpaRepository<FinalCtaSection, Long> {}