// src/main/java/com/cms/dto/ServiceFullDto.java
package com.example.ez.services.dto.servicedetailpage;

import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceFullDto {
    private ServiceHeroDto hero;
    private ServiceOverviewDto overview;
    private List<ServiceStatDto> stats;
    private List<ServiceModuleDto> modules;
    private List<ServiceBenefitDto> benefits;
    private List<ServiceFeatureDto> features;
    private List<ServiceTechSpecDto> techSpecs;
    private ServiceCtaDto cta;
    private List<ServiceRelatedDto> related;
}