// src/main/java/com/example/ez/cms/industry/entity/IndustryService.java
package com.example.ez.homepage.industry.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cms_industry_service")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class IndustryService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private String subtitle;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String feature;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    private Boolean active = true;
}