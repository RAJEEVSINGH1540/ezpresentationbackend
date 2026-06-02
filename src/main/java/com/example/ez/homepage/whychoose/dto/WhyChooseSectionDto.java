// src/main/java/com/example/ez/cms/whychoose/dto/WhyChooseSectionDto.java
package com.example.ez.homepage.whychoose.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WhyChooseSectionDto {
    private Long id;
    private String eyebrowText;
    private String headline;
    private String dashboardImageUrl;
    private List<WhyChooseBenefitDto> benefits;
}