// src/main/java/com/example/ez/cms/whychoose/entity/WhyChooseBenefit.java
package com.example.ez.homepage.whychoose.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cms_why_choose_benefit")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WhyChooseBenefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "num_label", nullable = false)
    private String numLabel;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;
}