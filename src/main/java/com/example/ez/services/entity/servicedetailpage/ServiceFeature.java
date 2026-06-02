// src/main/java/com/cms/entity/ServiceFeature.java
package com.example.ez.services.entity.servicedetailpage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_feature")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Column(name = "feature_text")
    private String feature;

    @Column(name = "sort_order")
    private Integer sortOrder;
}