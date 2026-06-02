// src/main/java/com/example/ez/dto/BookDemoConfigDTO.java
package com.example.ez.bookdemoform.dto;

import lombok.*;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDemoConfigDTO {
    private Map<String, String> configs;
}