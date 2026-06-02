// src/main/java/com/example/ez/cms/trusted/dto/TrustedSectionDto.java
package com.example.ez.homepage.trusted.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrustedSectionDto {
    private Long id;
    private String illustrationUrl;
    private String mainTagline;
    private String subTagline;
    private String trustedCountPrefix;
    private String trustedCountValue;
    private String trustedCountSuffix;
    private List<TrustedBrandDto> brands;
}