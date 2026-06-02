package com.example.ez.services.entity.servicepage;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "cms_testimonials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestimonialsSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "badge", length = 500)
    private String badge;

    @Column(name = "heading", length = 500)
    private String heading;

    @Column(name = "heading_highlight", length = 500)
    private String headingHighlight;

    // Testimonials: [{name, role, company, review, avatar, color, rating}]
    @Column(name = "testimonials_json", columnDefinition = "LONGTEXT")
    private String testimonialsJson;

    @Column(name = "primary_color", length = 20)
    private String primaryColor;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}