package com.example.ez.footter.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLink {
    private String platform;   // facebook | linkedin | twitter | instagram
    private String href;
}