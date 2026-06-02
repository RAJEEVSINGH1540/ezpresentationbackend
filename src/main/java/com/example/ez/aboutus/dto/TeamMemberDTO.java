package com.example.ez.aboutus.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberDTO {
    private Long id;
    private String name;
    private String designation;
    private String image;
    private String linkedinUrl;
    private String twitterUrl;
    private Integer sortOrder;
    private Boolean isActive;
}