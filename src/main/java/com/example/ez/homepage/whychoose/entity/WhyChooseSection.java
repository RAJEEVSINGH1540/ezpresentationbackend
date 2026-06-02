// src/main/java/com/example/ez/cms/whychoose/entity/WhyChooseSection.java
package com.example.ez.homepage.whychoose.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cms_why_choose_section")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WhyChooseSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "eyebrow_text")
    private String eyebrowText;

    @Column(name = "headline")
    private String headline;

    @Column(name = "dashboard_image_url", columnDefinition = "TEXT")
    private String dashboardImageUrl;
}