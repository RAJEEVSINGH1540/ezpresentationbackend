// src/main/java/com/cms/entity/ServiceModule.java
package com.example.ez.services.entity.servicedetailpage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_module")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @Column(name = "module_icon")
    private String icon;

    @Column(name = "module_title")
    private String title;

    @Column(name = "module_desc", columnDefinition = "TEXT")
    private String desc;

    @Column(name = "sort_order")
    private Integer sortOrder;
}