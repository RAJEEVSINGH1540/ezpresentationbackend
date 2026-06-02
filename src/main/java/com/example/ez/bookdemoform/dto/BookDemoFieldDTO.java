// src/main/java/com/example/ez/dto/BookDemoFieldDTO.java
package com.example.ez.bookdemoform.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDemoFieldDTO {
    private Long id;
    private String fieldName;
    private String label;
    private String fieldType;
    private String placeholder;
    private String options;
    private Boolean required;
    private Integer sortOrder;
    private Boolean isOptional;
    private String secondPlaceholder;
    private String secondLabel;
}