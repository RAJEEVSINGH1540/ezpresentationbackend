package com.example.ez.aboutus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "about_features")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AboutFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "about_page_id")
    private Long aboutPageId;

    @Column(name = "section_type")
    // "COMPANY_INFO" or "SOLUTIONS"
    private String sectionType;

    @Column(name = "icon_name")
    private String iconName;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "check_item")
    // for simple checkmark items under company info
    private Boolean checkItem;
}