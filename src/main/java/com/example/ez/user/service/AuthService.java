package com.example.ez.user.service;

import com.example.ez.user.dto.LoginRequest;
import com.example.ez.user.dto.LoginResponse;
import com.example.ez.user.entity.Admin;
import com.example.ez.user.exception.UnauthorizedException;
import com.example.ez.user.jwt.JwtUtil;
import com.example.ez.user.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CaptchaService captchaService;

    @Value("${ez.jwt.expiration-ms}")
    private long expirationMs;

    public LoginResponse login(LoginRequest request) {

        // ── 1. Validate captcha ────────────────────────────────────────────
        boolean captchaValid = captchaService.validateCaptcha(
                request.getCaptchaToken(), request.getCaptcha());
        if (!captchaValid) {
            log.warn("⚠️ [EZ] Captcha validation failed for email: {}", request.getEmail());
            throw new UnauthorizedException("Invalid or expired captcha. Please refresh and try again.");
        }

        // ── 2. Find admin by username (username field stores email) ─────────
        Admin admin = adminRepository
                .findByUsername(request.getEmail())
                .orElseThrow(() -> {
                    log.warn("⚠️ [EZ] Admin not found with email: {}", request.getEmail());
                    return new UnauthorizedException("Invalid email or password");
                });

        // ── 3. Verify password ─────────────────────────────────────────────
        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), admin.getPassword());
        log.debug("🔍 [EZ] Password match for '{}': {}", request.getEmail(), passwordMatches);

        if (!passwordMatches) {
            log.warn("⚠️ [EZ] Password mismatch for admin: {}", request.getEmail());
            throw new UnauthorizedException("Invalid email or password");
        }

        // ── 4. Generate JWT token ──────────────────────────────────────────
        String token = jwtUtil.generateToken(admin.getUsername(), admin.getRole());
        log.info("✅ [EZ] Admin '{}' logged in successfully", admin.getUsername());

        return LoginResponse.builder()
                .token(token)
                .username(admin.getUsername())
                .email(admin.getUsername()) // username is email
                .role(admin.getRole())
                .expiresInMs(expirationMs)
                .project("EZ")
                .build();
    }
}