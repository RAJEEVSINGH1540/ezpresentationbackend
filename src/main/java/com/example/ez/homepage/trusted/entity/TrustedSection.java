// src/main/java/com/example/ez/cms/trusted/entity/TrustedSection.java
package com.example.ez.homepage.trusted.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cms_trusted_section")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrustedSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "illustration_url", columnDefinition = "TEXT")
    private String illustrationUrl;

    @Column(name = "main_tagline", columnDefinition = "TEXT")
    private String mainTagline;

    @Column(name = "sub_tagline", columnDefinition = "TEXT")
    private String subTagline;

    @Column(name = "trusted_count_prefix")
    private String trustedCountPrefix;

    @Column(name = "trusted_count_value")
    private String trustedCountValue;

    @Column(name = "trusted_count_suffix")
    private String trustedCountSuffix;
}