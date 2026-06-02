// src/main/java/com/cms/entity/ServiceBenefit.java
package com.example.ez.services.entity.servicedetailpage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_benefit")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceBenefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Column(name = "benefit_title")
    private String title;

    @Column(name = "benefit_desc", columnDefinition = "TEXT")
    private String desc;

    @Column(name = "benefit_image")
    private String image;

    @Column(name = "sort_order")
    private Integer sortOrder;
}