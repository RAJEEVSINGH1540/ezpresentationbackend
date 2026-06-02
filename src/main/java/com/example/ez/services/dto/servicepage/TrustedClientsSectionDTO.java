package com.example.ez.services.dto.servicepage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrustedClientsSectionDTO {
    private Long id;
    private String sectionLabel;
    private String statsJson;
    private String logosJson;
    private String primaryColor;
    private Boolean isActive;
}