package com.example.ez.services.entity.servicepage;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "cms_final_cta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalCtaSection {

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

    @Column(name = "cta_primary_text", length = 300)
    private String ctaPrimaryText;

    @Column(name = "cta_secondary_text", length = 300)
    private String ctaSecondaryText;

    // Stats: [{label, value}]
    @Column(name = "stats_json", columnDefinition = "LONGTEXT")
    private String statsJson;

    // Trust items: [{icon, label}]
    @Column(name = "trust_items_json", columnDefinition = "LONGTEXT")
    private String trustItemsJson;

    // Floating cards: [{title, subtitle, badge}]
    @Column(name = "floating_cards_json", columnDefinition = "LONGTEXT")
    private String floatingCardsJson;

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