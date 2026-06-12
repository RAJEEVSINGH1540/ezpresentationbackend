package com.example.ez.erpwork.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "erp_services")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // e.g. "Rental ERP", "Facility ERP"

    @Column(nullable = false)
    private String slug; // e.g. "rental", "facility"

    private String description;
    private String iconUrl;
    private String cardImageUrl;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private int displayOrder = 0;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // ── Child sections ─────────────────────────────────────────────────────

    @OneToOne(mappedBy = "erpService", cascade = CascadeType.ALL,
              orphanRemoval = true, fetch = FetchType.LAZY)
    private ErpHeroSection heroSection;

    @OneToOne(mappedBy = "erpService", cascade = CascadeType.ALL,
              orphanRemoval = true, fetch = FetchType.LAZY)
    private ErpBusinessIntelligenceSection businessIntelligenceSection;

    @OneToOne(mappedBy = "erpService", cascade = CascadeType.ALL,
              orphanRemoval = true, fetch = FetchType.LAZY)
    private ErpCustomerSupportSection customerSupportSection;

    @OneToMany(mappedBy = "erpService", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("displayOrder ASC")
    private List<ErpFaqItem> faqItems = new ArrayList<>();

    @OneToMany(mappedBy = "erpService", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("displayOrder ASC")
    private List<ErpFeatureTab> featureTabs = new ArrayList<>();

    @OneToMany(mappedBy = "erpService", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("displayOrder ASC")
    private List<ErpProduct> products = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt  = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}