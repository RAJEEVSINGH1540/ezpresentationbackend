// src/main/java/com/example/ez/erp/dto/ErpProductFeatureDTO.java
package com.example.ez.erpwork.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ErpProductFeatureDTO {
    private Long id;
    private Long erpProductId;
    private String featureText;
    private int displayOrder;
}