// src/main/java/com/example/ez/cms/trusted/entity/TrustedBrand.java
package com.example.ez.homepage.trusted.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cms_trusted_brand")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrustedBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(nullable = false)
    private String name;

    @Column(name = "logo_url", columnDefinition = "TEXT")
    private String logoUrl;

    private Boolean active = true;
}