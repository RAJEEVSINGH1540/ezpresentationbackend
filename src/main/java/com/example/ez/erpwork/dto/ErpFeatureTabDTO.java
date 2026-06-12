package com.example.ez.erpwork.dto;

import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ErpFeatureTabDTO {
    private Long id;
    private Long erpServiceId;
    private String tabId;
    private String label;
    private String title;
    private String description1;
    private String description2;
    private String featuresLabel;
    private String imageUrl;
    private String imageAlt;
    private boolean usesDashboard;
    private int displayOrder;
    private List<ErpFeatureTabItemDTO> featureItems;
}