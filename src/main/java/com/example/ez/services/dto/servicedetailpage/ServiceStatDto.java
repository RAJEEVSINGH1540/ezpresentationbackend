// src/main/java/com/cms/dto/ServiceStatDto.java
package com.example.ez.services.dto.servicedetailpage;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceStatDto {
    private Long id;
    private String value;
    private String label;
    private Integer sortOrder;
}