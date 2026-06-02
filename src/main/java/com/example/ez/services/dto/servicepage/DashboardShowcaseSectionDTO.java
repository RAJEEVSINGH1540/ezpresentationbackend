package com.example.ez.services.dto.servicepage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardShowcaseSectionDTO {
    private Long id;
    private String badge;
    private String heading;
    private String subheading;
    private String ctaText;
    private String tabsJson;
    private String primaryColor;
    private Boolean isActive;
}