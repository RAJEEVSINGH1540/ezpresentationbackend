package com.example.ez.aboutus.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AboutFeatureDTO {
    private Long id;
    private String sectionType;
    private String iconName;
    private String title;
    private String description;
    private Integer sortOrder;
    private Boolean checkItem;
}