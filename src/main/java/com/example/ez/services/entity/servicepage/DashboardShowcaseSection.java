package com.example.ez.services.entity.servicepage;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "cms_dashboard_showcase")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardShowcaseSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "badge", length = 500)
    private String badge;

    @Column(name = "heading", length = 500)
    private String heading;

    @Column(name = "subheading", columnDefinition = "TEXT")
    private String subheading;

    @Column(name = "cta_text", length = 300)
    private String ctaText;

    // Tabs stored as JSON: {"Tab Name": {headline, desc, metrics:[{label,value,up}]}}
    @Column(name = "tabs_json", columnDefinition = "LONGTEXT")
    private String tabsJson;

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