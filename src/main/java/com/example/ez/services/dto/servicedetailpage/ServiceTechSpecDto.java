// src/main/java/com/cms/dto/ServiceTechSpecDto.java
package com.example.ez.services.dto.servicedetailpage;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceTechSpecDto {
    private Long id;
    private String label;
    private String value;
    private Integer sortOrder;
}