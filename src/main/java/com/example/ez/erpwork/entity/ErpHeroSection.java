package com.example.ez.erpwork.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "erp_hero_sections")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpHeroSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "erp_service_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ErpService erpService;

    private String badgeText;       // "EZ CONSTRUCTION® ERP"
    private String headline;        // "An ERP Software To Manage..."
    @Column(columnDefinition = "TEXT")
    private String description;
    private String ctaButtonText;   // "Get a Demo"
    private String ctaButtonLink;
    private String dashboardImageUrl;
}