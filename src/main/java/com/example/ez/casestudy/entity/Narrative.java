package com.example.ez.casestudy.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project_narratives")
@Data
public class Narrative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "narrative_points", joinColumns = @JoinColumn(name = "narrative_id"))
    @Column(name = "point", length = 500)
    private List<String> points = new ArrayList<>();
}