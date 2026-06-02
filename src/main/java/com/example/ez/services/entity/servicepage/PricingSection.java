package com.example.ez.services.entity.servicepage;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "cms_pricing")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricingSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "badge", length = 500)
    private String badge;

    @Column(name = "heading", length = 500)
    private String heading;

    @Column(name = "heading_highlight", length = 500)
    private String headingHighlight;

    @Column(name = "subheading", columnDefinition = "TEXT")
    private String subheading;

    @Column(name = "yearly_save_text", length = 200)
    private String yearlySaveText;

    @Column(name = "footer_note", columnDefinition = "TEXT")
    private String footerNote;

    // Plans: [{name, monthlyPrice, yearlyPrice, desc, features:[], notIncluded:[], cta, featured, dark}]
    @Column(name = "plans_json", columnDefinition = "LONGTEXT")
    private String plansJson;

    // Trust badges: [{icon, label}]
    @Column(name = "trust_badges_json", columnDefinition = "LONGTEXT")
    private String trustBadgesJson;

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