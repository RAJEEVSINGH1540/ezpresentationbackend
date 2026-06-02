package com.example.ez.aboutus.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AboutPageDTO {
    private Long id;

    // Hero
    private String heroTitle;
    private String heroBreadcrumb;

    // Company Info
    private String companyInfoHeading;
    private String companyInfoDescription;
    private String companyInfoImage1;
    private String companyInfoImage2;
    private String companyInfoImage3;
    private String companyInfoBtnText;
    private String companyInfoBtnLink;

    // Solutions
    private String solutionsHeading;
    private String solutionsImage1;
    private String solutionsImage2;

    // Stats
    private String stat1Number;
    private String stat1Label;
    private String stat2Number;
    private String stat2Label;
    private String stat3Number;
    private String stat3Label;
    private String stat4Number;
    private String stat4Label;

    // Team
    private String teamHeading;

    // CTA
    private String ctaHeading;
    private String ctaDescription;
    private String ctaBtnText;
    private String ctaBtnLink;

    // Contact
    private String contactHeading;
    private String contactDescription;
    private String contactAddress;
    private String contactEmail;
    private String contactPhone;
    private String contactHours;

    private Boolean isActive;

    // Relations
    private List<AboutFeatureDTO> companyInfoFeatures;
    private List<AboutFeatureDTO> solutionsFeatures;
    private List<TeamMemberDTO> teamMembers;
}