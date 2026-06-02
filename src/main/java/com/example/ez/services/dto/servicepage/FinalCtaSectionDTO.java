package com.example.ez.services.dto.servicepage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalCtaSectionDTO {
    private Long id;
    private String badge;
    private String heading;
    private String headingHighlight;
    private String subheading;
    private String ctaPrimaryText;
    private String ctaSecondaryText;
    private String statsJson;
    private String trustItemsJson;
    private String floatingCardsJson;
    private String primaryColor;
    private Boolean isActive;
}