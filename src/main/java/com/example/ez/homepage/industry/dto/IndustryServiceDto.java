// src/main/java/com/example/ez/cms/industry/dto/IndustryServiceDto.java
package com.example.ez.homepage.industry.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class IndustryServiceDto {

    private Long id;
    private Integer sortOrder;
    private String label;
    private String subtitle;
    private String description;
    private String feature;
    private String imageUrl;
    private Boolean active;
}