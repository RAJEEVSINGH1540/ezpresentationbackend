// src/main/java/com/example/ez/cms/hero/dto/HeroSectionDto.java
package com.example.ez.homepage.HeroSection.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HeroSectionDto {

    private Long id;
    private String eyebrowLabel;
    private String headlineLine1;
    private String headlineLine2;
    private String headlineHighlight;
    private String subheadline;

    private String stat1Value;
    private String stat1Label;
    private String stat2Value;
    private String stat2Label;
    private String stat3Value;
    private String stat3Label;
    private String stat4Value;
    private String stat4Label;

    private String bgImageUrl;
    private String dashboardImageUrl;
    private String overviewCardImageUrl;
    private String progressCardImageUrl;
}