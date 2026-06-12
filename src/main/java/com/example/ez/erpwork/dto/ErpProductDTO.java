package com.example.ez.erpwork.dto;

import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ErpProductDTO {
    private Long id;
    private Long erpServiceId;
    private String title;
    private String description;
    private String ctaButtonText;
    private String ctaButtonLink;
    private int displayOrder;
    private String sectionBadgeText;
    private String sectionTitle;
    private String sectionDescription;
    private List<ErpProductFeatureDTO> features;
}