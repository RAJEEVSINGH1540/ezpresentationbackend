package com.example.ez.homepage.trusted.controller;

import com.example.ez.homepage.trusted.dto.TrustedBrandDto;
import com.example.ez.homepage.trusted.dto.TrustedSectionDto;
import com.example.ez.homepage.trusted.service.TrustedBrandsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/homepage/trusted-brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TrustedBrandsController {

    private final TrustedBrandsService service;

    // ── Section endpoints ────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<TrustedSectionDto> getSection() {
        return ResponseEntity.ok(service.getSection());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrustedSectionDto> updateSection(
            @PathVariable Long id,
            @RequestBody TrustedSectionDto dto) {
        return ResponseEntity.ok(service.updateSection(id, dto));
    }

    @PostMapping("/{id}/upload-illustration")
    public ResponseEntity<TrustedSectionDto> uploadIllustration(
            @PathVariable Long id,
            @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadIllustration(id, file));
    }

    // ── Brand endpoints ──────────────────────────────────────────────
    @GetMapping("/brands")
    public ResponseEntity<List<TrustedBrandDto>> getAllBrands() {
        return ResponseEntity.ok(service.getAllBrands());
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<TrustedBrandDto> getBrand(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBrand(id));
    }

    @PostMapping("/brands")
    public ResponseEntity<TrustedBrandDto> createBrand(
            @RequestBody TrustedBrandDto dto) {
        return ResponseEntity.ok(service.createBrand(dto));
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<TrustedBrandDto> updateBrand(
            @PathVariable Long id,
            @RequestBody TrustedBrandDto dto) {
        return ResponseEntity.ok(service.updateBrand(id, dto));
    }

    @DeleteMapping("/brands/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        service.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/brands/{id}/upload-logo")
    public ResponseEntity<TrustedBrandDto> uploadLogo(
            @PathVariable Long id,
            @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadBrandLogo(id, file));
    }
}