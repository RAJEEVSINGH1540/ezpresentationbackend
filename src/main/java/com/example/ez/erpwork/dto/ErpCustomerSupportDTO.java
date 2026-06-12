package com.example.ez.erpwork.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ErpCustomerSupportDTO {
    private Long id;
    private Long erpServiceId;
    private String title;
    private String description;
    private String ctaButtonText;
    private String ctaButtonLink;
    private String dashboardImageUrl;
    private String portalBadgeInitial;
    private String portalBadgeLabel;
}