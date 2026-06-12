package com.example.ez.erpwork.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ErpHeroSectionDTO {
    private Long id;
    private Long erpServiceId;
    private String badgeText;
    private String headline;
    private String description;
    private String ctaButtonText;
    private String ctaButtonLink;
    private String dashboardImageUrl;
}