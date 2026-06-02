// src/main/java/com/example/ez/cms/hero/entity/HeroSection.java
package com.example.ez.homepage.HeroSection.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cms_hero_section")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HomeHeroSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "eyebrow_label")
    private String eyebrowLabel;

    @Column(name = "headline_line1")
    private String headlineLine1;

    @Column(name = "headline_line2")
    private String headlineLine2;

    @Column(name = "headline_highlight")
    private String headlineHighlight;

    @Column(columnDefinition = "TEXT")
    private String subheadline;

    // Stats
    @Column(name = "stat1_value")
    private String stat1Value;
    @Column(name = "stat1_label")
    private String stat1Label;

    @Column(name = "stat2_value")
    private String stat2Value;
    @Column(name = "stat2_label")
    private String stat2Label;

    @Column(name = "stat3_value")
    private String stat3Value;
    @Column(name = "stat3_label")
    private String stat3Label;

    @Column(name = "stat4_value")
    private String stat4Value;
    @Column(name = "stat4_label")
    private String stat4Label;

    // Images
    @Column(name = "bg_image_url")
    private String bgImageUrl;

    @Column(name = "dashboard_image_url")
    private String dashboardImageUrl;

    @Column(name = "overview_card_image_url")
    private String overviewCardImageUrl;

    @Column(name = "progress_card_image_url")
    private String progressCardImageUrl;
}