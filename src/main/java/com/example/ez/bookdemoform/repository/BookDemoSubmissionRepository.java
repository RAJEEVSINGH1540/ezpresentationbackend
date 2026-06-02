// src/main/java/com/example/ez/repository/BookDemoSubmissionRepository.java
package com.example.ez.bookdemoform.repository;

import com.example.ez.bookdemoform.entity.BookDemoSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookDemoSubmissionRepository extends JpaRepository<BookDemoSubmission, Long> {
    List<BookDemoSubmission> findAllByOrderBySubmittedAtDesc();
    List<BookDemoSubmission> findByStatusOrderBySubmittedAtDesc(String status);
    long countByStatus(String status);
}