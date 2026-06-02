// src/main/java/com/example/ez/cms/trusted/dto/TrustedBrandDto.java
package com.example.ez.homepage.trusted.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TrustedBrandDto {
    private Long id;
    private Integer sortOrder;
    private String name;
    private String logoUrl;
    private Boolean active;
}