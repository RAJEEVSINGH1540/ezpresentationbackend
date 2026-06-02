package com.example.ez.aboutus.repository;

import com.example.ez.aboutus.entity.AboutPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AboutPageRepository extends JpaRepository<AboutPage, Long> {
    Optional<AboutPage> findFirstByIsActiveTrue();
}