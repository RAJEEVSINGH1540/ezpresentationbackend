// src/main/java/com/cms/dto/ServiceBenefitDto.java
package com.example.ez.services.dto.servicedetailpage;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceBenefitDto {
    private Long id;
    private String title;
    private String desc;
    private String image;
    private Integer sortOrder;
}