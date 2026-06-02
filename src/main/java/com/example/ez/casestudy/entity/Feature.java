package com.example.ez.casestudy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "project_features")
@Data
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

@Column(name = "description", length = 1000)
    private String desc; // Field name matches DTO "desc"
}