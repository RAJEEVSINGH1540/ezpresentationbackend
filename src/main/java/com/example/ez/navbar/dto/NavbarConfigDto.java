// src/main/java/com/example/ez/homepage/navbar/NavbarConfigDto.java
package com.example.ez.navbar.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NavbarConfigDto {

    private Long    id;
    private String  logoImageUrl;
    private String  logoText;
    private String  logoIconColor;
    private List<NavLinkDto> navLinks;   // deserialized list
    private String  ctaLabel;
    private String  ctaHref;
    private String  ctaColor;
    private Boolean sticky;
    private Boolean active;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class NavLinkDto {
        private String  label;
        private String  href;
        private Boolean isExternal;
    }
}