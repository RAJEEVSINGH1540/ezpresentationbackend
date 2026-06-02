package com.example.ez.aboutus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "about_page")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AboutPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Hero Section
    @Column(name = "hero_title")
    private String heroTitle;

    @Column(name = "hero_breadcrumb")
    private String heroBreadcrumb;

    // Company Info Section
    @Column(name = "company_info_heading", length = 500)
    private String companyInfoHeading;

    @Column(name = "company_info_description", columnDefinition = "TEXT")
    private String companyInfoDescription;

    @Column(name = "company_info_image1")
    private String companyInfoImage1;

    @Column(name = "company_info_image2")
    private String companyInfoImage2;

    @Column(name = "company_info_image3")
    private String companyInfoImage3;

    @Column(name = "company_info_btn_text")
    private String companyInfoBtnText;

    @Column(name = "company_info_btn_link")
    private String companyInfoBtnLink;

    // IT Solutions Section
    @Column(name = "solutions_heading", length = 500)
    private String solutionsHeading;

    @Column(name = "solutions_image1")
    private String solutionsImage1;

    @Column(name = "solutions_image2")
    private String solutionsImage2;

    // Stats Section
    @Column(name = "stat1_number")
    private String stat1Number;

    @Column(name = "stat1_label")
    private String stat1Label;

    @Column(name = "stat2_number")
    private String stat2Number;

    @Column(name = "stat2_label")
    private String stat2Label;

    @Column(name = "stat3_number")
    private String stat3Number;

    @Column(name = "stat3_label")
    private String stat3Label;

    @Column(name = "stat4_number")
    private String stat4Number;

    @Column(name = "stat4_label")
    private String stat4Label;

    // Team Section
    @Column(name = "team_heading", length = 500)
    private String teamHeading;

    // CTA Section
    @Column(name = "cta_heading", length = 500)
    private String ctaHeading;

    @Column(name = "cta_description", columnDefinition = "TEXT")
    private String ctaDescription;

    @Column(name = "cta_btn_text")
    private String ctaBtnText;

    @Column(name = "cta_btn_link")
    private String ctaBtnLink;

    // Contact Form Section
    @Column(name = "contact_heading")
    private String contactHeading;

    @Column(name = "contact_description", columnDefinition = "TEXT")
    private String contactDescription;

    @Column(name = "contact_address", columnDefinition = "TEXT")
    private String contactAddress;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_hours")
    private String contactHours;

    @Column(name = "is_active")
    private Boolean isActive;
}