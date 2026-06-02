// src/main/java/com/cms/entity/ServiceRelated.java
package com.example.ez.services.entity.servicedetailpage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_related")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceRelated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Column(name = "related_service_id")
    private String relatedServiceId;

    @Column(name = "sort_order")
    private Integer sortOrder;
}