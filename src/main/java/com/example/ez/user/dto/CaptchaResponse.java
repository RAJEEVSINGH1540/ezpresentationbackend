package com.example.ez.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaptchaResponse {
    private String captchaToken;
    private String captchaImageBase64;
}