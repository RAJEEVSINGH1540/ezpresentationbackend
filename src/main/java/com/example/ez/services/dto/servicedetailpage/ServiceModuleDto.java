// src/main/java/com/cms/dto/ServiceModuleDto.java
package com.example.ez.services.dto.servicedetailpage;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceModuleDto {
    private Long id;
    private String icon;
    private String title;
    private String desc;
    private Integer sortOrder;
}