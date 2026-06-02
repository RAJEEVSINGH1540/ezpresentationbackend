package com.example.ez.casestudy.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "project_process")
@Data
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phase;

@Column(name = "description", length = 1000)
    private String desc; // Field name matches DTO "desc"
    
    private String weeks;
}