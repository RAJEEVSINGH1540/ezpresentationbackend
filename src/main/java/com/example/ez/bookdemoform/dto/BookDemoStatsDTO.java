// src/main/java/com/example/ez/dto/BookDemoStatsDTO.java
package com.example.ez.bookdemoform.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDemoStatsDTO {
    private long total;
    private long pending;
    private long verified;
    private long rejected;
}