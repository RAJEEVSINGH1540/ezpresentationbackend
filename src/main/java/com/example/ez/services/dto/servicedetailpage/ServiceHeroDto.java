// src/main/java/com/cms/dto/ServiceHeroDto.java
package com.example.ez.services.dto.servicedetailpage;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceHeroDto {
    private String serviceId;
    private String title;
    private String subtitle;
    private String tagline;
    private String description;
    private String heroImage;
    private String color;
    private String tag;
}