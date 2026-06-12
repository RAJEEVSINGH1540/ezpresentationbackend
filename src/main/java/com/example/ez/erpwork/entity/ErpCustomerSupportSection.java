package com.example.ez.erpwork.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "erp_customer_support_sections")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpCustomerSupportSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "erp_service_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ErpService erpService;

    private String title;           // "Customer Support"

    @Column(columnDefinition = "TEXT")
    private String description;

    private String ctaButtonText;
    private String ctaButtonLink;
    private String dashboardImageUrl;

    // Portal badge fields
    private String portalBadgeInitial;  // "K"
    private String portalBadgeLabel;    // "Customer Portal"
}