// src/main/java/com/cms/entity/ServiceHero.java
package com.example.ez.services.entity.servicedetailpage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_hero")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceHero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id", unique = true, nullable = false)
    private String serviceId;

    @Column(name = "hero_title")
    private String title;

    @Column(name = "hero_subtitle")
    private String subtitle;

    @Column(name = "hero_tagline")
    private String tagline;

    @Column(name = "hero_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "hero_image")
    private String heroImage;

    @Column(name = "brand_color")
    private String color;

    @Column(name = "service_tag")
    private String tag;
}