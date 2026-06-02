// src/main/java/com/example/ez/cms/whychoose/dto/WhyChooseBenefitDto.java
package com.example.ez.homepage.whychoose.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WhyChooseBenefitDto {
    private Long id;
    private Integer sortOrder;
    private String numLabel;
    private String title;
    private String description;
}