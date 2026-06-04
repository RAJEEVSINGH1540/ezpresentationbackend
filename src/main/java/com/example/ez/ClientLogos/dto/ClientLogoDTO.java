// src/main/java/com/example/ez/dto/ClientLogoDTO.java
package com.example.ez.ClientLogos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientLogoDTO {

    private Long id;

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name must be under 100 characters")
    private String companyName;

    @NotBlank(message = "Logo URL is required")
    @Size(max = 500, message = "Logo URL must be under 500 characters")
    private String logoUrl;

    @Size(max = 300, message = "Website URL must be under 300 characters")
    private String websiteUrl;

    @Min(value = 0, message = "Display order must be non-negative")
    private Integer displayOrder;

    private Boolean active;
}