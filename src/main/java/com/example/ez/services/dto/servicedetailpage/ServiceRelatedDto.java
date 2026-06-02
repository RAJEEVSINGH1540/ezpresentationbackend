// src/main/java/com/cms/dto/ServiceRelatedDto.java
package com.example.ez.services.dto.servicedetailpage;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceRelatedDto {
    private Long id;
    private String relatedServiceId;
    private Integer sortOrder;
}