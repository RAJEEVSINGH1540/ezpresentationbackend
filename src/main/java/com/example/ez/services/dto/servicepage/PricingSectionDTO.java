package com.example.ez.services.dto.servicepage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricingSectionDTO {
    private Long id;
    private String badge;
    private String heading;
    private String headingHighlight;
    private String subheading;
    private String yearlySaveText;
    private String footerNote;
    private String plansJson;
    private String trustBadgesJson;
    private String primaryColor;
    private Boolean isActive;
}