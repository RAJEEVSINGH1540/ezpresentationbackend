package com.example.ez.erpwork.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "erp_feature_tab_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpFeatureTabItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_tab_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ErpFeatureTab featureTab;

    @Column(columnDefinition = "TEXT")
    private String leftText;

    @Column(columnDefinition = "TEXT")
    private String rightText;

    private int displayOrder = 0;
}