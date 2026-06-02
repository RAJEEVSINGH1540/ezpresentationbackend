package com.example.ez.casestudy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Meta meta;

    @Column(name = "hero_image", length = 1000)
    private String heroImage;

    // ✅ EAGER
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "project_results",
            joinColumns = @JoinColumn(name = "project_id")
    )
    private List<Result> results = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "challenge_id")
    private Narrative challenge;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "solution_id")
    private Narrative solution;

    // ✅ EAGER
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private List<TechStack> techStack = new ArrayList<>();

    // ✅ EAGER
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private List<Process> process = new ArrayList<>();

    // ✅ EAGER
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private List<Feature> features = new ArrayList<>();

    // ✅ EAGER — this was the crash cause
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "project_gallery",
            joinColumns = @JoinColumn(name = "project_id")
    )
    @Column(name = "image_url", length = 1000)
    private List<String> gallery = new ArrayList<>();

    @Embedded
    private Testimonial testimonial;
}