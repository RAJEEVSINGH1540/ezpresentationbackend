package com.example.ez.erpwork.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ErpServiceFullDTO {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String iconUrl;
    private String cardImageUrl;
    private boolean active;
    private int displayOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ErpHeroSectionDTO heroSection;
    private ErpBISectionDTO businessIntelligenceSection;
    private ErpCustomerSupportDTO customerSupportSection;
    private List<ErpFaqItemDTO> faqItems;
    private List<ErpFeatureTabDTO> featureTabs;
    private List<ErpProductDTO> products;
}