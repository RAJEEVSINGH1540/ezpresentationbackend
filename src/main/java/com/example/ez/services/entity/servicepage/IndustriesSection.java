package com.example.ez.services.entity.servicepage;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "cms_industries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndustriesSection {

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

    // Industries: [{name, desc, icon, color}]
    @Column(name = "industries_json", columnDefinition = "LONGTEXT")
    private String industriesJson;

    @Column(name = "cta_card_title", length = 300)
    private String ctaCardTitle;

    @Column(name = "cta_card_desc", columnDefinition = "TEXT")
    private String ctaCardDesc;

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