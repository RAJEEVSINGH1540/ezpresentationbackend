// src/main/java/com/example/ez/entity/BookDemoConfig.java
package com.example.ez.bookdemoform.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_demo_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDemoConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String configKey; // e.g. "dialog_title", "dialog_subtitle", "badge_text", etc.

    @Column(columnDefinition = "TEXT", nullable = false)
    private String configValue;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    protected void onSave() {
        updatedAt = LocalDateTime.now();
    }
}