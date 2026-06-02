// src/main/java/com/cms/entity/ServiceCta.java
package com.example.ez.services.entity.servicedetailpage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_cta")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ServiceCta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_id", unique = true, nullable = false)
    private String serviceId;

    @Column(name = "badge_text")
    private String badgeText;

    @Column(name = "cta_heading")
    private String heading;

    @Column(name = "sub_text", columnDefinition = "TEXT")
    private String subText;

    @Column(name = "button_label")
    private String buttonLabel;

    @Column(name = "trust_point_1")
    private String trustPoint1;

    @Column(name = "trust_point_2")
    private String trustPoint2;

    @Column(name = "trust_point_3")
    private String trustPoint3;

    @Column(name = "trust_point_4")
    private String trustPoint4;

    @Column(name = "roi_label")
    private String roiLabel;

    @Column(name = "roi_value")
    private String roiValue;

    @Column(name = "go_live_label")
    private String goLiveLabel;

    @Column(name = "go_live_value")
    private String goLiveValue;

    @Column(name = "go_live_subtext")
    private String goLiveSubtext;
}