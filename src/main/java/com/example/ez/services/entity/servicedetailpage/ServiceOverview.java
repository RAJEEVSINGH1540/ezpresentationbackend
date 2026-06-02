// src/main/java/com/cms/entity/ServiceOverview.java
package com.example.ez.services.entity.servicedetailpage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_overview")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceOverview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id", unique = true, nullable = false)
    private String serviceId;

    @Column(name = "overview_text", columnDefinition = "TEXT")
    private String overview;
}