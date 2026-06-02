package com.example.ez.casestudy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Testimonial {
    @Column(length = 2000)
    private String quote;
    private String author;
    @Column(name = "author_role")
    private String role;
}