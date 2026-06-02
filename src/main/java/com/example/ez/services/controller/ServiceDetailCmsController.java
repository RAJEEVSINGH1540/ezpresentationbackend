// src/main/java/com/cms/controller/ServiceDetailCmsController.java
package com.example.ez.services.controller;

import com.example.ez.services.dto.servicedetailpage.*;
import com.example.ez.services.service.ImageUploadService;
import com.example.ez.services.service.ServiceDetailCmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cms/services")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ServiceDetailCmsController {

    private final ServiceDetailCmsService svc;
    private final ImageUploadService imgSvc;

    // ── List all service IDs ─────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<String>> listServiceIds() {
        return ResponseEntity.ok(svc.getAllServiceIds());
    }

    // ── Get full service ─────────────────────────────────────────
    @GetMapping("/{serviceId}/full")
    public ResponseEntity<ServiceFullDto> getFull(@PathVariable String serviceId) {
        return ResponseEntity.ok(svc.getFull(serviceId));
    }

    // ── Hero ─────────────────────────────────────────────────────
    @GetMapping("/{serviceId}/hero")
    public ResponseEntity<ServiceHeroDto> getHero(@PathVariable String serviceId) {
        return ResponseEntity.ok(svc.getFull(serviceId).getHero());
    }

    @PutMapping("/{serviceId}/hero")
    public ResponseEntity<ServiceHeroDto> saveHero(
        @PathVariable String serviceId,
        @RequestBody ServiceHeroDto dto
    ) {
        return ResponseEntity.ok(svc.saveHero(serviceId, dto));
    }

    // ── Overview ─────────────────────────────────────────────────
    @PutMapping("/{serviceId}/overview")
    public ResponseEntity<ServiceOverviewDto> saveOverview(
        @PathVariable String serviceId,
        @RequestBody ServiceOverviewDto dto
    ) {
        return ResponseEntity.ok(svc.saveOverview(serviceId, dto));
    }

    // ── Stats ────────────────────────────────────────────────────
    @PutMapping("/{serviceId}/stats")
    public ResponseEntity<List<ServiceStatDto>> saveStats(
        @PathVariable String serviceId,
        @RequestBody List<ServiceStatDto> dtos
    ) {
        return ResponseEntity.ok(svc.saveStats(serviceId, dtos));
    }

    // ── Modules ──────────────────────────────────────────────────
    @PutMapping("/{serviceId}/modules")
    public ResponseEntity<List<ServiceModuleDto>> saveModules(
        @PathVariable String serviceId,
        @RequestBody List<ServiceModuleDto> dtos
    ) {
        return ResponseEntity.ok(svc.saveModules(serviceId, dtos));
    }

    // ── Benefits ─────────────────────────────────────────────────
    @PutMapping("/{serviceId}/benefits")
    public ResponseEntity<List<ServiceBenefitDto>> saveBenefits(
        @PathVariable String serviceId,
        @RequestBody List<ServiceBenefitDto> dtos
    ) {
        return ResponseEntity.ok(svc.saveBenefits(serviceId, dtos));
    }

    // ── Features ─────────────────────────────────────────────────
    @PutMapping("/{serviceId}/features")
    public ResponseEntity<List<ServiceFeatureDto>> saveFeatures(
        @PathVariable String serviceId,
        @RequestBody List<ServiceFeatureDto> dtos
    ) {
        return ResponseEntity.ok(svc.saveFeatures(serviceId, dtos));
    }

    // ── TechSpecs ────────────────────────────────────────────────
    @PutMapping("/{serviceId}/tech-specs")
    public ResponseEntity<List<ServiceTechSpecDto>> saveTechSpecs(
        @PathVariable String serviceId,
        @RequestBody List<ServiceTechSpecDto> dtos
    ) {
        return ResponseEntity.ok(svc.saveTechSpecs(serviceId, dtos));
    }

    // ── CTA ──────────────────────────────────────────────────────
    @PutMapping("/{serviceId}/cta")
    public ResponseEntity<ServiceCtaDto> saveCta(
        @PathVariable String serviceId,
        @RequestBody ServiceCtaDto dto
    ) {
        return ResponseEntity.ok(svc.saveCta(serviceId, dto));
    }

    // ── Related ──────────────────────────────────────────────────
    @PutMapping("/{serviceId}/related")
    public ResponseEntity<List<ServiceRelatedDto>> saveRelated(
        @PathVariable String serviceId,
        @RequestBody List<ServiceRelatedDto> dtos
    ) {
        return ResponseEntity.ok(svc.saveRelated(serviceId, dtos));
    }

    // ── Image Upload ─────────────────────────────────────────────
    @PostMapping("/upload-image")
    public ResponseEntity<Map<String, String>> uploadImage(
        @RequestParam("file") MultipartFile file
    ) throws IOException {
        String url = imgSvc.upload(file);
        return ResponseEntity.ok(Map.of("url", url));
    }
    // Add to ServiceDetailCmsController.java

    // ── Delete service (all related data) ────────────────────────
    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable String serviceId) {
        svc.deleteService(serviceId);
        return ResponseEntity.noContent().build();
    }
}