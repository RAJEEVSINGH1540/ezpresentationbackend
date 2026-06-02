// src/main/java/com/example/ez/repository/BookDemoConfigRepository.java
package com.example.ez.bookdemoform.repository;

import com.example.ez.bookdemoform.entity.BookDemoConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookDemoConfigRepository extends JpaRepository<BookDemoConfig, Long> {
    Optional<BookDemoConfig> findByConfigKey(String configKey);
}