package com.example.ez.erpwork.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "erp_bi_sections")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpBusinessIntelligenceSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "erp_service_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ErpService erpService;

    private String headingPart1;    // "Business Intelligence, to get"
    private String headingHighlight;// "deeper insights"
    private String headingPart2;    // "about your real estate business"

    @Column(columnDefinition = "TEXT")
    private String paragraph1;

    @Column(columnDefinition = "TEXT")
    private String paragraph2;

    @Column(columnDefinition = "TEXT")
    private String paragraph3;

    private String ctaButtonText;
    private String ctaButtonLink;

    // Images
    private String overviewCardImageUrl;
    private String progressCardImageUrl;
    private String dashboardImageUrl;
}