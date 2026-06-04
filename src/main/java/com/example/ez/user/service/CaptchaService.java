package com.example.ez.user.service;

import com.example.ez.user.dto.CaptchaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class CaptchaService {

    private static final int WIDTH = 160;
    private static final int HEIGHT = 50;
    private static final int CODE_LENGTH = 5;
    private static final long EXPIRY_SECONDS = 300; // 5 minutes
    private static final String CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

    private final SecureRandom random = new SecureRandom();
    private final Map<String, CaptchaEntry> captchaStore = new ConcurrentHashMap<>();

    public CaptchaResponse generateCaptcha() {
        String code = generateCode();
        String token = generateToken();

        BufferedImage image = createImage(code);
        String base64 = toBase64(image);

        captchaStore.put(token, new CaptchaEntry(code, Instant.now().plusSeconds(EXPIRY_SECONDS)));
        log.debug("Generated captcha token: {}", token);

        return CaptchaResponse.builder()
                .captchaToken(token)
                .captchaImageBase64("data:image/png;base64," + base64)
                .build();
    }

    public boolean validateCaptcha(String token, String userInput) {
        if (token == null || userInput == null) return false;

        CaptchaEntry entry = captchaStore.get(token);
        if (entry == null) return false;

        // Remove after validation (one-time use)
        captchaStore.remove(token);

        if (Instant.now().isAfter(entry.expiry())) {
            log.warn("Captcha expired for token: {}", token);
            return false;
        }

        boolean valid = entry.code().equalsIgnoreCase(userInput.trim());
        log.debug("Captcha validation: {} for token: {}", valid, token);
        return valid;
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

    private String generateToken() {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private BufferedImage createImage(String code) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Background
        g2d.setColor(new Color(240, 240, 245));
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // Noise lines
        g2d.setStroke(new BasicStroke(1.5f));
        for (int i = 0; i < 8; i++) {
            g2d.setColor(new Color(
                100 + random.nextInt(100),
                100 + random.nextInt(100),
                150 + random.nextInt(100)
            ));
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Noise dots
        for (int i = 0; i < 100; i++) {
            g2d.setColor(new Color(
                150 + random.nextInt(100),
                150 + random.nextInt(100),
                150 + random.nextInt(100)
            ));
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            g2d.fillRect(x, y, 2, 2);
        }

        // Text
        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        int charWidth = WIDTH / (code.length() + 1);
        for (int i = 0; i < code.length(); i++) {
            g2d.setColor(new Color(
                20 + random.nextInt(80),
                20 + random.nextInt(80),
                100 + random.nextInt(80)
            ));
            // Slight rotation
            int x = (i + 1) * charWidth - 15;
            int y = 35 + random.nextInt(8) - 4;
            g2d.rotate((random.nextDouble() - 0.5) * 0.4, x + 10, y);
            g2d.drawString(String.valueOf(code.charAt(i)), x, y);
            g2d.rotate(-(random.nextDouble() - 0.5) * 0.4, x + 10, y);
        }

        g2d.dispose();
        return image;
    }

    private String toBase64(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            log.error("Failed to encode captcha image", e);
            throw new RuntimeException("Captcha generation failed");
        }
    }

    private record CaptchaEntry(String code, Instant expiry) {}
}