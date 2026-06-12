package com.example.ez.erpwork.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ErpFeatureTabItemDTO {
    private Long id;
    private Long featureTabId;
    private String leftText;
    private String rightText;
    private int displayOrder;
}