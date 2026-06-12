package com.example.ez.erpwork.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "erp_product_features")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpProductFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "erp_product_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ErpProduct erpProduct;

    private String featureText;
    private int displayOrder = 0;
}