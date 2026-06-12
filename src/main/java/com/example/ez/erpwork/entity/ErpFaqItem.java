package com.example.ez.erpwork.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "erp_faq_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpFaqItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "erp_service_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ErpService erpService;

    @Column(nullable = false)
    private String question;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String answer;

    private int displayOrder = 0;

    // ── FIXED: renamed from "column" to "faqColumn" ──────────────
    // "column" is a reserved word in MySQL → use a safe column name
    @Column(name = "faq_column")
    private String faqColumn = "left";

    private String sectionBadgeText;
    private String sectionTitle;

    @Column(columnDefinition = "TEXT")
    private String sectionDescription;
}