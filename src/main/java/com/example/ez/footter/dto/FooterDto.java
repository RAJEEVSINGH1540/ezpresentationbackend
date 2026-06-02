package com.example.ez.footter.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FooterDto {
    private Long   id;
    private String logoUrl;
    private String description;
    private String copyrightText;
    private String quickLinksHeading;
    private String servicesHeading;
    private String informationHeading;

    private List<SocialLink> socialLinks;
    private List<LinkItem>   quickLinks;
    private List<LinkItem>   serviceLinks;
    private List<LinkItem>   infoLinks;
}