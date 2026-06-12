package com.example.ez.erpwork.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ErpBISectionDTO {
    private Long id;
    private Long erpServiceId;
    private String headingPart1;
    private String headingHighlight;
    private String headingPart2;
    private String paragraph1;
    private String paragraph2;
    private String paragraph3;
    private String ctaButtonText;
    private String ctaButtonLink;
    private String overviewCardImageUrl;
    private String progressCardImageUrl;
    private String dashboardImageUrl;
}