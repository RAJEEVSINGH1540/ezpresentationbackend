package com.example.ez.footter.controller;

import com.example.ez.footter.dto.FooterDto;
import com.example.ez.footter.service.FooterService;
import com.example.ez.services.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/cms/footer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FooterController {

    private final FooterService      footerService;
    private final ImageUploadService imageUploadService;

    // ── Public (live site) ────────────────────────────────────
    @GetMapping("/public")
    public ResponseEntity<FooterDto> getPublic() {
        return ResponseEntity.ok(footerService.get());
    }

    // ── CMS ───────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<FooterDto> get() {
        return ResponseEntity.ok(footerService.get());
    }

    @PutMapping
    public ResponseEntity<FooterDto> save(@RequestBody FooterDto dto) {
        return ResponseEntity.ok(footerService.save(dto));
    }

    @PostMapping("/upload-logo")
    public ResponseEntity<String> uploadLogo(@RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(imageUploadService.upload(file));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Upload failed: " + e.getMessage());
        }
    }
}