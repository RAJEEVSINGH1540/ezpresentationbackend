// src/main/java/com/example/ez/repository/BookDemoFieldRepository.java
package com.example.ez.bookdemoform.repository;

import com.example.ez.bookdemoform.entity.BookDemoField;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookDemoFieldRepository extends JpaRepository<BookDemoField, Long> {
    List<BookDemoField> findAllByOrderBySortOrderAsc();
}