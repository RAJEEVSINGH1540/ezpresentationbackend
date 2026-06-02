// src/main/java/com/example/ez/entity/BookDemoField.java
package com.example.ez.bookdemoform.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_demo_fields")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDemoField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fieldName; // e.g. "your_name", "company_name"

    @Column(nullable = false)
    private String label; // e.g. "Your name", "Company name"

    @Column(nullable = false)
    private String fieldType; // TEXT, EMAIL, PHONE, SELECT, TEXTAREA, SPLIT_TEXT

    private String placeholder; // e.g. "Enter your name"

    @Column(columnDefinition = "TEXT")
    private String options; // JSON array for SELECT type: ["India","USA","UK"]

    @Column(nullable = false)
    private Boolean required;

    @Column(nullable = false)
    private Integer sortOrder;

    private Boolean isOptional; // show "(Optional)" label

    // For SPLIT_TEXT type (like first name + last name in one row)
    private String secondPlaceholder;
    private String secondLabel;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}