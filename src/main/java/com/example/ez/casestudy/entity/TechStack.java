package com.example.ez.casestudy.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project_tech_stack")
@Data
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @ElementCollection
    @CollectionTable(name = "tech_tools", joinColumns = @JoinColumn(name = "tech_stack_id"))
    @Column(name = "tool_name")
    private List<String> tools = new ArrayList<>();
}