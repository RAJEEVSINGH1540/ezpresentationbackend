package com.example.ez.erpwork.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpServiceDTO {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String iconUrl;
    private String cardImageUrl;
    private boolean active;
    private int displayOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}