package com.example.ez.services.entity.servicepage;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "cms_trusted_clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrustedClientsSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "section_label", length = 300)
    private String sectionLabel;

    // Stats stored as JSON array: [{value, label}]
    @Column(name = "stats_json", columnDefinition = "LONGTEXT")
    private String statsJson;

    // Logos stored as JSON array: [{name, imageUrl}]
    @Column(name = "logos_json", columnDefinition = "LONGTEXT")
    private String logosJson;

    @Column(name = "primary_color", length = 20)
    private String primaryColor;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}