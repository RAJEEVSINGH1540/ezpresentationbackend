package com.example.ez.homepage.trusted.repository;

import com.example.ez.homepage.trusted.entity.TrustedSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrustedSectionRepository extends JpaRepository<TrustedSection, Long> {}