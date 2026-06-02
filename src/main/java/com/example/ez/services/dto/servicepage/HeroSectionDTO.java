package com.example.ez.services.dto.servicepage;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class HeroSectionDTO {
    private Long id;
    private String badge;
    private String heading;
    private String headingHighlight;
    private String subheading;
    private String ctaPrimaryText;
    private String ctaSecondaryText;
    private String badgeColor;
    private String primaryColor;
    private String imageUrl;
    private Boolean isActive;
}