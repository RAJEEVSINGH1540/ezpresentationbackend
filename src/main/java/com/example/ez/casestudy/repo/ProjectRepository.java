package com.example.ez.casestudy.repo;

import com.example.ez.casestudy.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // You can add custom finders here if needed, e.g.:
    // Optional<Project> findByMetaTitle(String title);
}