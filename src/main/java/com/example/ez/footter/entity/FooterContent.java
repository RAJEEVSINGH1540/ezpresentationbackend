package com.example.ez.footter.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "footer_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FooterContent {

    @Id
    @Column(name = "id")
    private Long id;   // Always 1 — no @GeneratedValue so we control it

    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "copyright_text", length = 300)
    private String copyrightText;

    @Column(name = "social_links_json", columnDefinition = "TEXT")
    private String socialLinksJson;

    @Column(name = "quick_links_json", columnDefinition = "TEXT")
    private String quickLinksJson;

    @Column(name = "service_links_json", columnDefinition = "TEXT")
    private String serviceLinksJson;

    @Column(name = "info_links_json", columnDefinition = "TEXT")
    private String infoLinksJson;

    @Column(name = "quick_links_heading", length = 100)
    private String quickLinksHeading;

    @Column(name = "services_heading", length = 100)
    private String servicesHeading;

    @Column(name = "information_heading", length = 100)
    private String informationHeading;
}