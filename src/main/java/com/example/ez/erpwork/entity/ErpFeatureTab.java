package com.example.ez.erpwork.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "erp_feature_tabs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpFeatureTab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "erp_service_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ErpService erpService;

    private String tabId;           // "scalable", "sales", etc.
    private String label;           // "Scalable Platform"
    private String title;           // "Build on a platform..."
    
    @Column(columnDefinition = "TEXT")
    private String description1;
    
    @Column(columnDefinition = "TEXT")
    private String description2;
    
    private String featuresLabel;   // "Features that power scalability:"
    private String imageUrl;
    private String imageAlt;
    private boolean usesDashboard = false;
    private int displayOrder = 0;

    @OneToMany(mappedBy = "featureTab", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("displayOrder ASC")
    private List<ErpFeatureTabItem> featureItems = new ArrayList<>();
}