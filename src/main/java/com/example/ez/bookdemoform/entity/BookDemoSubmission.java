// src/main/java/com/example/ez/entity/BookDemoSubmission.java
package com.example.ez.bookdemoform.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_demo_submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDemoSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String formData; // JSON string of all submitted field values

    private String email; // extracted for quick access

    private String name; // extracted for quick access

    @Column(nullable = false)
    private String status; // PENDING, VERIFIED, REJECTED

    private LocalDateTime submittedAt;
    private LocalDateTime verifiedAt;

    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
        if (status == null) status = "PENDING";
    }
}