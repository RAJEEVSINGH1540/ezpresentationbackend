package com.example.ez.user.service;

import com.example.ez.email.EmailService;
import com.example.ez.user.dto.ResetPasswordRequest;
import com.example.ez.user.entity.Admin;
import com.example.ez.user.entity.PasswordResetToken;
import com.example.ez.user.exception.UnauthorizedException;
import com.example.ez.user.repository.AdminRepository;
import com.example.ez.user.repository.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;
    private final AdminRepository adminRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.frontend-url:http://localhost:5173}")
    private String frontendUrl;

    private static final long TOKEN_EXPIRY_MINUTES = 30;
    private final SecureRandom random = new SecureRandom();

    @Transactional
    public void requestPasswordReset(String email) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(email);
        if (adminOpt.isEmpty()) {
            log.warn("Password reset requested for non-existent email: {}", email);
            return;
        }

        // Invalidate old tokens
        tokenRepository.findByEmailAndUsedFalse(email)
                .ifPresent(old -> {
                    old.setUsed(true);
                    tokenRepository.save(old);
                });

        String token = generateToken();
        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(token)
                .email(email)
                .expiry(Instant.now().plusSeconds(TOKEN_EXPIRY_MINUTES * 60))
                .used(false)
                .build();

        tokenRepository.save(resetToken);

        // 🔥 BUILD FULL RESET URL
        String resetLink = frontendUrl + "/admin/reset-password?token=" + token;
        emailService.sendPasswordResetEmail(email, resetLink);

        log.info("Password reset token generated for: {}", email);
    }

    @Transactional
    public void resetPassword(ResetPasswordRequest request) {
        PasswordResetToken token = tokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new UnauthorizedException("Invalid or expired reset token"));

        if (token.isUsed()) {
            throw new UnauthorizedException("Reset token already used");
        }

        if (Instant.now().isAfter(token.getExpiry())) {
            throw new UnauthorizedException("Reset token has expired");
        }

        Admin admin = adminRepository.findByUsername(token.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Account not found"));

        admin.setPassword(passwordEncoder.encode(request.getNewPassword()));
        adminRepository.save(admin);

        token.setUsed(true);
        tokenRepository.save(token);

        log.info("Password reset successful for: {}", token.getEmail());
    }

    public boolean isTokenValid(String token) {
        return tokenRepository.findByToken(token)
                .filter(t -> !t.isUsed() && Instant.now().isBefore(t.getExpiry()))
                .isPresent();
    }

    private String generateToken() {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}