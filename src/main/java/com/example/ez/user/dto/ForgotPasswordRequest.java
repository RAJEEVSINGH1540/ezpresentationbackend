package com.example.ez.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Captcha is required")
    private String captcha;

    @NotBlank(message = "Captcha token is required")
    private String captchaToken;
}