// src/main/java/com/cms/dto/ServiceFeatureDto.java
package com.example.ez.services.dto.servicedetailpage;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceFeatureDto {
    private Long id;
    private String feature;
    private Integer sortOrder;
}