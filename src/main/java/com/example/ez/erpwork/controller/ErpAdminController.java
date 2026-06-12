package com.example.ez.erpwork.controller;

import com.example.ez.erpwork.dto.*;
import com.example.ez.erpwork.service.ErpCmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/erp/admin")
//@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class ErpAdminController {

    private final ErpCmsService cmsService;

    // ══════════════════════════════════════════════════════════════════════
    // IMAGE UPLOAD
    // ══════════════════════════════════════════════════════════════════════

    @PostMapping(value = "/upload/{subfolder}",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadImage(
            @RequestParam("file") MultipartFile file,
            @PathVariable String subfolder) throws IOException {
        String url = cmsService.uploadImage(file, subfolder);
        return ResponseEntity.ok(Map.of("url", url));
    }

    // ══════════════════════════════════════════════════════════════════════
    // ERP SERVICES
    // ══════════════════════════════════════════════════════════════════════

    @GetMapping("/services")
    public ResponseEntity<List<ErpServiceDTO>> getAllServices() {
        return ResponseEntity.ok(cmsService.getAllServices());
    }

    @GetMapping("/services/{id}")
    public ResponseEntity<ErpServiceFullDTO> getService(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getServiceFull(id));
    }

    @PostMapping("/services")
    public ResponseEntity<ErpServiceDTO> createService(
            @RequestBody ErpServiceDTO dto) {
        return ResponseEntity.ok(cmsService.createService(dto));
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<ErpServiceDTO> updateService(
            @PathVariable Long id,
            @RequestBody ErpServiceDTO dto) {
        return ResponseEntity.ok(cmsService.updateService(id, dto));
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<Map<String, String>> deleteService(
            @PathVariable Long id) {
        cmsService.deleteService(id);
        return ResponseEntity.ok(Map.of("message", "Service deleted"));
    }

    // ── Upload card image for service ────────────────────────────────────
    @PostMapping(value = "/services/{id}/card-image",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadCardImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {
        String url = cmsService.uploadImage(file, "service-cards");
        ErpServiceDTO dto = cmsService.getAllServices().stream()
                .filter(s -> s.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Not found"));
        dto.setCardImageUrl(url);
        cmsService.updateService(id, dto);
        return ResponseEntity.ok(Map.of("url", url));
    }

    // ══════════════════════════════════════════════════════════════════════
    // HERO SECTION
    // ══════════════════════════════════════════════════════════════════════

    @GetMapping("/services/{id}/hero")
    public ResponseEntity<ErpHeroSectionDTO> getHero(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getHeroSection(id));
    }

    @PutMapping("/services/{id}/hero")
    public ResponseEntity<ErpHeroSectionDTO> saveHero(
            @PathVariable Long id,
            @RequestBody ErpHeroSectionDTO dto) {
        return ResponseEntity.ok(cmsService.saveHeroSection(id, dto));
    }

    @PostMapping(value = "/services/{id}/hero/image",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadHeroImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {
        String url = cmsService.uploadImage(file, "hero");
        ErpHeroSectionDTO existing = cmsService.getHeroSection(id);
        if (existing == null) existing = new ErpHeroSectionDTO();
        existing.setDashboardImageUrl(url);
        cmsService.saveHeroSection(id, existing);
        return ResponseEntity.ok(Map.of("url", url));
    }

    // ══════════════════════════════════════════════════════════════════════
    // BUSINESS INTELLIGENCE SECTION
    // ══════════════════════════════════════════════════════════════════════

    @GetMapping("/services/{id}/business-intelligence")
    public ResponseEntity<ErpBISectionDTO> getBI(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getBISection(id));
    }

    @PutMapping("/services/{id}/business-intelligence")
    public ResponseEntity<ErpBISectionDTO> saveBI(
            @PathVariable Long id,
            @RequestBody ErpBISectionDTO dto) {
        return ResponseEntity.ok(cmsService.saveBISection(id, dto));
    }

    @PostMapping(value = "/services/{id}/business-intelligence/image/{type}",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadBIImage(
            @PathVariable Long id,
            @PathVariable String type,
            @RequestParam("file") MultipartFile file) throws IOException {
        String url = cmsService.uploadImage(file, "bi");
        ErpBISectionDTO existing = cmsService.getBISection(id);
        if (existing == null) existing = new ErpBISectionDTO();
        switch (type) {
            case "overview"  -> existing.setOverviewCardImageUrl(url);
            case "progress"  -> existing.setProgressCardImageUrl(url);
            case "dashboard" -> existing.setDashboardImageUrl(url);
        }
        cmsService.saveBISection(id, existing);
        return ResponseEntity.ok(Map.of("url", url));
    }

    // ══════════════════════════════════════════════════════════════════════
    // CUSTOMER SUPPORT SECTION
    // ══════════════════════════════════════════════════════════════════════

    @GetMapping("/services/{id}/customer-support")
    public ResponseEntity<ErpCustomerSupportDTO> getCS(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getCSSection(id));
    }

    @PutMapping("/services/{id}/customer-support")
    public ResponseEntity<ErpCustomerSupportDTO> saveCS(
            @PathVariable Long id,
            @RequestBody ErpCustomerSupportDTO dto) {
        return ResponseEntity.ok(cmsService.saveCSSection(id, dto));
    }

    @PostMapping(value = "/services/{id}/customer-support/image",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadCSImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {
        String url = cmsService.uploadImage(file, "customer-support");
        ErpCustomerSupportDTO existing = cmsService.getCSSection(id);
        if (existing == null) existing = new ErpCustomerSupportDTO();
        existing.setDashboardImageUrl(url);
        cmsService.saveCSSection(id, existing);
        return ResponseEntity.ok(Map.of("url", url));
    }

    // ══════════════════════════════════════════════════════════════════════
    // FAQ ITEMS
    // ══════════════════════════════════════════════════════════════════════

    @GetMapping("/services/{id}/faqs")
    public ResponseEntity<List<ErpFaqItemDTO>> getFaqs(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getFaqs(id));
    }

    @PostMapping("/services/{id}/faqs")
    public ResponseEntity<ErpFaqItemDTO> createFaq(
            @PathVariable Long id,
            @RequestBody ErpFaqItemDTO dto) {
        return ResponseEntity.ok(cmsService.createFaq(id, dto));
    }

    @PutMapping("/services/{serviceId}/faqs/{faqId}")
    public ResponseEntity<ErpFaqItemDTO> updateFaq(
            @PathVariable Long serviceId,
            @PathVariable Long faqId,
            @RequestBody ErpFaqItemDTO dto) {
        return ResponseEntity.ok(cmsService.updateFaq(faqId, dto));
    }

    @DeleteMapping("/services/{serviceId}/faqs/{faqId}")
    public ResponseEntity<Map<String, String>> deleteFaq(
            @PathVariable Long serviceId,
            @PathVariable Long faqId) {
        cmsService.deleteFaq(faqId);
        return ResponseEntity.ok(Map.of("message", "FAQ deleted"));
    }

    // ══════════════════════════════════════════════════════════════════════
    // FEATURE TABS
    // ══════════════════════════════════════════════════════════════════════

    @GetMapping("/services/{id}/feature-tabs")
    public ResponseEntity<List<ErpFeatureTabDTO>> getTabs(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getFeatureTabs(id));
    }

    @PostMapping("/services/{id}/feature-tabs")
    public ResponseEntity<ErpFeatureTabDTO> createTab(
            @PathVariable Long id,
            @RequestBody ErpFeatureTabDTO dto) {
        return ResponseEntity.ok(cmsService.createFeatureTab(id, dto));
    }

    @PutMapping("/services/{serviceId}/feature-tabs/{tabId}")
    public ResponseEntity<ErpFeatureTabDTO> updateTab(
            @PathVariable Long serviceId,
            @PathVariable Long tabId,
            @RequestBody ErpFeatureTabDTO dto) {
        return ResponseEntity.ok(cmsService.updateFeatureTab(tabId, dto));
    }

    @DeleteMapping("/services/{serviceId}/feature-tabs/{tabId}")
    public ResponseEntity<Map<String, String>> deleteTab(
            @PathVariable Long serviceId,
            @PathVariable Long tabId) {
        cmsService.deleteFeatureTab(tabId);
        return ResponseEntity.ok(Map.of("message", "Tab deleted"));
    }

    @PostMapping(value = "/services/{serviceId}/feature-tabs/{tabId}/image",
                 consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadTabImage(
            @PathVariable Long serviceId,
            @PathVariable Long tabId,
            @RequestParam("file") MultipartFile file) throws IOException {
        String url = cmsService.uploadImage(file, "feature-tabs");
        ErpFeatureTabDTO existing = cmsService.getFeatureTabs(serviceId)
                .stream().filter(t -> t.getId().equals(tabId))
                .findFirst().orElseThrow();
        existing.setImageUrl(url);
        cmsService.updateFeatureTab(tabId, existing);
        return ResponseEntity.ok(Map.of("url", url));
    }

    // ══════════════════════════════════════════════════════════════════════
    // PRODUCTS
    // ══════════════════════════════════════════════════════════════════════

    @GetMapping("/services/{id}/products")
    public ResponseEntity<List<ErpProductDTO>> getProducts(@PathVariable Long id) {
        return ResponseEntity.ok(cmsService.getProducts(id));
    }

    @PostMapping("/services/{id}/products")
    public ResponseEntity<ErpProductDTO> createProduct(
            @PathVariable Long id,
            @RequestBody ErpProductDTO dto) {
        return ResponseEntity.ok(cmsService.createProduct(id, dto));
    }

    @PutMapping("/services/{serviceId}/products/{productId}")
    public ResponseEntity<ErpProductDTO> updateProduct(
            @PathVariable Long serviceId,
            @PathVariable Long productId,
            @RequestBody ErpProductDTO dto) {
        return ResponseEntity.ok(cmsService.updateProduct(productId, dto));
    }

    @DeleteMapping("/services/{serviceId}/products/{productId}")
    public ResponseEntity<Map<String, String>> deleteProduct(
            @PathVariable Long serviceId,
            @PathVariable Long productId) {
        cmsService.deleteProduct(productId);
        return ResponseEntity.ok(Map.of("message", "Product deleted"));
    }
}