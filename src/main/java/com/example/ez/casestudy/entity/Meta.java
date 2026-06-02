package com.example.ez.casestudy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
@Embeddable
@Data
public class Meta {
    private String title;
    private String client;
    private String category;
    @Column(name = "project_year") // 'year' is a reserved keyword in some DBs
    private String year;
    private String duration;
    private String role;
    private String website;
}