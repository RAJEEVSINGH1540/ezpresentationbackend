package com.example.ez.homepage.whychoose.controller;

import com.example.ez.homepage.whychoose.dto.WhyChooseBenefitDto;
import com.example.ez.homepage.whychoose.dto.WhyChooseSectionDto;
import com.example.ez.homepage.whychoose.service.WhyChooseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/homepage/why-choose-us")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WhyChooseController {

    private final WhyChooseService service;

    // ── Section endpoints ────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<WhyChooseSectionDto> getSection() {
        return ResponseEntity.ok(service.getSection());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WhyChooseSectionDto> updateSection(
            @PathVariable Long id,
            @RequestBody WhyChooseSectionDto dto) {
        return ResponseEntity.ok(service.updateSection(id, dto));
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<WhyChooseSectionDto> uploadImage(
            @PathVariable Long id,
            @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadDashboardImage(id, file));
    }

    // ── Benefit endpoints ────────────────────────────────────────────
    @GetMapping("/benefits")
    public ResponseEntity<List<WhyChooseBenefitDto>> getAllBenefits() {
        return ResponseEntity.ok(service.getAllBenefits());
    }

    @GetMapping("/benefits/{id}")
    public ResponseEntity<WhyChooseBenefitDto> getBenefit(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getBenefit(id));
    }

    @PostMapping("/benefits")
    public ResponseEntity<WhyChooseBenefitDto> createBenefit(
            @RequestBody WhyChooseBenefitDto dto) {
        return ResponseEntity.ok(service.createBenefit(dto));
    }

    @PutMapping("/benefits/{id}")
    public ResponseEntity<WhyChooseBenefitDto> updateBenefit(
            @PathVariable Long id,
            @RequestBody WhyChooseBenefitDto dto) {
        return ResponseEntity.ok(service.updateBenefit(id, dto));
    }

    @DeleteMapping("/benefits/{id}")
    public ResponseEntity<Void> deleteBenefit(@PathVariable Long id) {
        service.deleteBenefit(id);
        return ResponseEntity.noContent().build();
    }
}