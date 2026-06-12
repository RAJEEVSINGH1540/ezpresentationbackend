// src/main/java/com/example/ez/erp/entity/ErpProduct.java
package com.example.ez.erpwork.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "erp_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "erp_service_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ErpService erpService;

    private String title;           // "Rental ERP"
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String ctaButtonText;
    private String ctaButtonLink;
    private int displayOrder = 0;

    // Products section heading (stored on first product or via service)
    private String sectionBadgeText;
    private String sectionTitle;
    private String sectionDescription;

    @OneToMany(mappedBy = "erpProduct", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("displayOrder ASC")
    private List<ErpProductFeature> features = new ArrayList<>();
}