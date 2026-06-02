package com.example.ez.footter.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LinkItem {
    private String label;
    private String href;
}