// src/main/java/com/cms/dto/ServiceCtaDto.java
package com.example.ez.services.dto.servicedetailpage;

import lombok.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceCtaDto {
    private String badgeText;
    private String heading;
    private String subText;
    private String buttonLabel;
    private List<String> trustPoints;
    private String roiLabel;
    private String roiValue;
    private String goLiveLabel;
    private String goLiveValue;
    private String goLiveSubtext;
}