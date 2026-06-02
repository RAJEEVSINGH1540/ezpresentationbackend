// src/main/java/com/cms/entity/ServiceStat.java
package com.example.ez.services.entity.servicedetailpage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_stat")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Column(name = "stat_value")
    private String value;

    @Column(name = "stat_label")
    private String label;

    @Column(name = "sort_order")
    private Integer sortOrder;
}