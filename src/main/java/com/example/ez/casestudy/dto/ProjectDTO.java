package com.example.ez.casestudy.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO {



@Valid
@NotNull
private MetaDTO meta;

private String heroImage;

// --- CHANGED: Replaced 'stats' with 'results' ---
private List<ResultDTO> results;

@Valid
private NarrativeDTO challenge;

@Valid
private NarrativeDTO solution;

private List<TechStackDTO> techStack;

private List<ProcessDTO> process;

private List<FeatureDTO> features;

// Stores list of image URLs
private List<String> gallery;

private TestimonialDTO testimonial;

// --- Nested Static Classes for Structure ---

@Data
public static class MetaDTO {
    @NotBlank(message = "Title is required")
    private String title;

    private String client;
    private String category;
    private String year;
    private String duration;
    private String role;
    private String website;
}

// --- NEW DTO for Results ---
@Data
public static class ResultDTO {
    private String metric;
    private String value;
    private String period;
}

@Data
public static class NarrativeDTO {
    private String description;
    private List<String> points;
}

@Data
public static class TechStackDTO {
    private String category;
    private List<String> tools;
}

@Data
public static class ProcessDTO {
    private String phase;
    private String desc;
    private String weeks;
}

@Data
public static class FeatureDTO {
    private String title;
    private String desc;
}

@Data
public static class TestimonialDTO {
    private String quote;
    private String author;
    private String role;
}
}