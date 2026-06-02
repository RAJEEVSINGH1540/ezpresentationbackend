package com.example.ez.services.controller;

import com.example.ez.services.dto.servicepage.*;
import com.example.ez.services.service.CmsSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/cms")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CmsSectionController {

    private final CmsSectionService service;

    // ════════════ GENERIC SECTION ENDPOINT (for frontend) ════════════
    @GetMapping("/sections/{sectionKey}")
    public ResponseEntity<?> getSectionByKey(@PathVariable String sectionKey) {
        return switch (sectionKey) {
            case "hero"                -> ResponseEntity.ok(service.getHero());
            case "trusted_clients"     -> ResponseEntity.ok(service.getTrustedClients());
            case "dashboard_showcase"  -> ResponseEntity.ok(service.getDashboard());
            case "why_choose_us"       -> ResponseEntity.ok(service.getWhyChooseUs());
            case "industries"          -> ResponseEntity.ok(service.getIndustries());
            case "benefits"            -> ResponseEntity.ok(service.getBenefits());
            case "testimonials"        -> ResponseEntity.ok(service.getTestimonials());
            case "pricing"             -> ResponseEntity.ok(service.getPricing());
            case "final_cta"           -> ResponseEntity.ok(service.getFinalCta());
            default -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "timestamp", Instant.now().toString(),
                            "status", 404,
                            "error", "Not Found",
                            "message", "Unknown section key: " + sectionKey,
                            "path", "/api/cms/sections/" + sectionKey
                    ));
        };
    }

    @PutMapping("/sections/{sectionKey}")
    public ResponseEntity<?> updateSectionByKey(
            @PathVariable String sectionKey,
            @RequestBody Map<String, Object> payload) {
        // Extract id from payload if present
        Long id = payload.containsKey("id")
                ? Long.valueOf(payload.get("id").toString()) : 1L;
        return switch (sectionKey) {
            case "hero"               -> getHero();
            case "trusted_clients"    -> getTrustedClients();
            case "dashboard_showcase" -> getDashboard();
            case "why_choose_us"      -> getWhyChooseUs();
            case "industries"         -> getIndustries();
            case "benefits"           -> getBenefits();
            case "testimonials"       -> getTestimonials();
            case "pricing"            -> getPricing();
            case "final_cta"          -> getFinalCta();
            default -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Unknown section: " + sectionKey));
        };
    }

    // ════════════ HERO ════════════
    @GetMapping("/hero")
    public ResponseEntity<?> getHero() {
        return ResponseEntity.ok(service.getHero());
    }

    @GetMapping("/hero/{id}")
    public ResponseEntity<?> getHeroById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getHeroById(id));
    }

    @PostMapping("/hero")
    public ResponseEntity<?> createHero(@RequestBody HeroSectionDTO dto) {
        return ResponseEntity.ok(service.createHero(dto));
    }

    @PutMapping("/hero/{id}")
    public ResponseEntity<?> updateHero(@PathVariable Long id, @RequestBody HeroSectionDTO dto) {
        return ResponseEntity.ok(service.updateHero(id, dto));
    }

    @DeleteMapping("/hero/{id}")
    public ResponseEntity<?> deleteHero(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteHero(id));
    }

    // ════════════ TRUSTED CLIENTS ════════════
    @GetMapping("/trusted-clients")
    public ResponseEntity<?> getTrustedClients() {
        return ResponseEntity.ok(service.getTrustedClients());
    }

    @GetMapping("/trusted-clients/{id}")
    public ResponseEntity<?> getTrustedClientsById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTrustedClientsById(id));
    }

    @PostMapping("/trusted-clients")
    public ResponseEntity<?> createTrustedClients(@RequestBody TrustedClientsSectionDTO dto) {
        return ResponseEntity.ok(service.createTrustedClients(dto));
    }

    @PutMapping("/trusted-clients/{id}")
    public ResponseEntity<?> updateTrustedClients(@PathVariable Long id, @RequestBody TrustedClientsSectionDTO dto) {
        return ResponseEntity.ok(service.updateTrustedClients(id, dto));
    }

    @DeleteMapping("/trusted-clients/{id}")
    public ResponseEntity<?> deleteTrustedClients(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteTrustedClients(id));
    }

    // ════════════ DASHBOARD SHOWCASE ════════════
    @GetMapping("/dashboard-showcase")
    public ResponseEntity<?> getDashboard() {
        return ResponseEntity.ok(service.getDashboard());
    }

    @GetMapping("/dashboard-showcase/{id}")
    public ResponseEntity<?> getDashboardById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDashboardById(id));
    }

    @PostMapping("/dashboard-showcase")
    public ResponseEntity<?> createDashboard(@RequestBody DashboardShowcaseSectionDTO dto) {
        return ResponseEntity.ok(service.createDashboard(dto));
    }

    @PutMapping("/dashboard-showcase/{id}")
    public ResponseEntity<?> updateDashboard(@PathVariable Long id, @RequestBody DashboardShowcaseSectionDTO dto) {
        return ResponseEntity.ok(service.updateDashboard(id, dto));
    }

    @DeleteMapping("/dashboard-showcase/{id}")
    public ResponseEntity<?> deleteDashboard(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteDashboard(id));
    }

    // ════════════ WHY CHOOSE US ════════════
    @GetMapping("/why-choose-us")
    public ResponseEntity<?> getWhyChooseUs() {
        return ResponseEntity.ok(service.getWhyChooseUs());
    }

    @GetMapping("/why-choose-us/{id}")
    public ResponseEntity<?> getWhyChooseUsById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getWhyChooseUsById(id));
    }

    @PostMapping("/why-choose-us")
    public ResponseEntity<?> createWhyChooseUs(@RequestBody WhyChooseUsSectionDTO dto) {
        return ResponseEntity.ok(service.createWhyChooseUs(dto));
    }

    @PutMapping("/why-choose-us/{id}")
    public ResponseEntity<?> updateWhyChooseUs(@PathVariable Long id, @RequestBody WhyChooseUsSectionDTO dto) {
        return ResponseEntity.ok(service.updateWhyChooseUs(id, dto));
    }

    @DeleteMapping("/why-choose-us/{id}")
    public ResponseEntity<?> deleteWhyChooseUs(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteWhyChooseUs(id));
    }

    // ════════════ INDUSTRIES ════════════
    @GetMapping("/industries")
    public ResponseEntity<?> getIndustries() {
        return ResponseEntity.ok(service.getIndustries());
    }

    @GetMapping("/industries/{id}")
    public ResponseEntity<?> getIndustriesById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getIndustriesById(id));
    }

    @PostMapping("/industries")
    public ResponseEntity<?> createIndustries(@RequestBody IndustriesSectionDTO dto) {
        return ResponseEntity.ok(service.createIndustries(dto));
    }

    @PutMapping("/industries/{id}")
    public ResponseEntity<?> updateIndustries(@PathVariable Long id, @RequestBody IndustriesSectionDTO dto) {
        return ResponseEntity.ok(service.updateIndustries(id, dto));
    }

    @DeleteMapping("/industries/{id}")
    public ResponseEntity<?> deleteIndustries(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteIndustries(id));
    }

    // ════════════ BENEFITS ════════════
    @GetMapping("/benefits")
    public ResponseEntity<?> getBenefits() {
        return ResponseEntity.ok(service.getBenefits());
    }

    @GetMapping("/benefits/{id}")
    public ResponseEntity<?> getBenefitsById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBenefitsById(id));
    }

    @PostMapping("/benefits")
    public ResponseEntity<?> createBenefits(@RequestBody BenefitsSectionDTO dto) {
        return ResponseEntity.ok(service.createBenefits(dto));
    }

    @PutMapping("/benefits/{id}")
    public ResponseEntity<?> updateBenefits(@PathVariable Long id, @RequestBody BenefitsSectionDTO dto) {
        return ResponseEntity.ok(service.updateBenefits(id, dto));
    }

    @DeleteMapping("/benefits/{id}")
    public ResponseEntity<?> deleteBenefits(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteBenefits(id));
    }

    // ════════════ TESTIMONIALS ════════════
    @GetMapping("/testimonials")
    public ResponseEntity<?> getTestimonials() {
        return ResponseEntity.ok(service.getTestimonials());
    }

    @GetMapping("/testimonials/{id}")
    public ResponseEntity<?> getTestimonialsById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTestimonialsById(id));
    }

    @PostMapping("/testimonials")
    public ResponseEntity<?> createTestimonials(@RequestBody TestimonialsSectionDTO dto) {
        return ResponseEntity.ok(service.createTestimonials(dto));
    }

    @PutMapping("/testimonials/{id}")
    public ResponseEntity<?> updateTestimonials(@PathVariable Long id, @RequestBody TestimonialsSectionDTO dto) {
        return ResponseEntity.ok(service.updateTestimonials(id, dto));
    }

    @DeleteMapping("/testimonials/{id}")
    public ResponseEntity<?> deleteTestimonials(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteTestimonials(id));
    }

    // ════════════ PRICING ════════════
    @GetMapping("/pricing")
    public ResponseEntity<?> getPricing() {
        return ResponseEntity.ok(service.getPricing());
    }

    @GetMapping("/pricing/{id}")
    public ResponseEntity<?> getPricingById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPricingById(id));
    }

    @PostMapping("/pricing")
    public ResponseEntity<?> createPricing(@RequestBody PricingSectionDTO dto) {
        return ResponseEntity.ok(service.createPricing(dto));
    }

    @PutMapping("/pricing/{id}")
    public ResponseEntity<?> updatePricing(@PathVariable Long id, @RequestBody PricingSectionDTO dto) {
        return ResponseEntity.ok(service.updatePricing(id, dto));
    }

    @DeleteMapping("/pricing/{id}")
    public ResponseEntity<?> deletePricing(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletePricing(id));
    }

    // ════════════ FINAL CTA ════════════
    @GetMapping("/final-cta")
    public ResponseEntity<?> getFinalCta() {
        return ResponseEntity.ok(service.getFinalCta());
    }

    @GetMapping("/final-cta/{id}")
    public ResponseEntity<?> getFinalCtaById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFinalCtaById(id));
    }

    @PostMapping("/final-cta")
    public ResponseEntity<?> createFinalCta(@RequestBody FinalCtaSectionDTO dto) {
        return ResponseEntity.ok(service.createFinalCta(dto));
    }

    @PutMapping("/final-cta/{id}")
    public ResponseEntity<?> updateFinalCta(@PathVariable Long id, @RequestBody FinalCtaSectionDTO dto) {
        return ResponseEntity.ok(service.updateFinalCta(id, dto));
    }

    @DeleteMapping("/final-cta/{id}")
    public ResponseEntity<?> deleteFinalCta(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteFinalCta(id));
    }
}