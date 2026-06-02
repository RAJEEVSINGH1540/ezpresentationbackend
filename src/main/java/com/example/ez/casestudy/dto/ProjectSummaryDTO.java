package com.example.ez.casestudy.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectSummaryDTO {
    private Long id;
    private String title;
    private String client;
    private String category;
    private String year;
    private String description;
    private String impact; // e.g., "+245% Revenue"
    private String image;
    private List<String> tags; // e.g., ["React", "AWS"]
}