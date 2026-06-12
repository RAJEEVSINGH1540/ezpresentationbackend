package com.example.ez.erpwork.controller;

import com.example.ez.erpwork.dto.*;
import com.example.ez.erpwork.service.ErpCmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/erp/public")
@RequiredArgsConstructor
public class ErpPublicController {

    private final ErpCmsService cmsService;

    // ── Services listing (for cards on home/services page) ──────────────
    @GetMapping("/services")
    public ResponseEntity<List<ErpServiceDTO>> getActiveServices() {
        return ResponseEntity.ok(cmsService.getActiveServices());
    }

    // ── Full service detail by slug ──────────────────────────────────────
    @GetMapping("/services/{slug}")
    public ResponseEntity<ErpServiceFullDTO> getServiceBySlug(
            @PathVariable String slug) {
        return ResponseEntity.ok(cmsService.getServiceFullBySlug(slug));
    }

    // ── Individual sections by serviceId ─────────────────────────────────
    @GetMapping("/services/{id}/hero")
    public ResponseEntity<ErpHeroSectionDTO> getHero(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getHeroSection(id));
    }

    @GetMapping("/services/{id}/business-intelligence")
    public ResponseEntity<ErpBISectionDTO> getBI(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getBISection(id));
    }

    @GetMapping("/services/{id}/customer-support")
    public ResponseEntity<ErpCustomerSupportDTO> getCS(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getCSSection(id));
    }

    @GetMapping("/services/{id}/faqs")
    public ResponseEntity<List<ErpFaqItemDTO>> getFaqs(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getFaqs(id));
    }

    @GetMapping("/services/{id}/feature-tabs")
    public ResponseEntity<List<ErpFeatureTabDTO>> getTabs(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getFeatureTabs(id));
    }

    @GetMapping("/services/{id}/products")
    public ResponseEntity<List<ErpProductDTO>> getProducts(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getProducts(id));
    }
}