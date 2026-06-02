// src/main/java/com/example/ez/homepage/navbar/NavbarConfig.java
package com.example.ez.navbar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "navbar_config")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NavbarConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ── Logo ──────────────────────────────────────────────
    private String logoImageUrl;          // uploaded image or null
    private String logoText;              // fallback text e.g. "EZ Construction"
    private String logoIconColor;         // e.g. "#1a56e8"

    // ── Nav Links (stored as JSON array string) ───────────
    @Column(columnDefinition = "TEXT")
    private String navLinksJson;          // JSON: [{label, href, isExternal}]

    // ── CTA Button ───────────────────────────────────────
    private String ctaLabel;             // "Explore Platform"
    private String ctaHref;              // "/services"
    private String ctaColor;             // "#1a56e8"

    // ── Settings ──────────────────────────────────────────
    private Boolean sticky;              // sticky top-0
    private Boolean active;
}