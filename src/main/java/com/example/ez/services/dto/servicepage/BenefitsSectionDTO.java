package com.example.ez.services.dto.servicepage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenefitsSectionDTO {
    private Long id;
    private String badge;
    private String heading;
    private String headingHighlight;
    private String benefitsJson;
    private String primaryColor;
    private Boolean isActive;
}
