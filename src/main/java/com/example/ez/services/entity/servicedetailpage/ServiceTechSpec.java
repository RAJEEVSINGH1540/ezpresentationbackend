// src/main/java/com/cms/entity/ServiceTechSpec.java
package com.example.ez.services.entity.servicedetailpage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_tech_spec")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceTechSpec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Column(name = "spec_label")
    private String label;

    @Column(name = "spec_value")
    private String value;

    @Column(name = "sort_order")
    private Integer sortOrder;
}