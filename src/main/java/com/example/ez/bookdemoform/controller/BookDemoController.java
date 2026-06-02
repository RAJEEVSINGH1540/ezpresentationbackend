package com.example.ez.bookdemoform.controller;

import com.example.ez.bookdemoform.dto.BookDemoConfigDTO;
import com.example.ez.bookdemoform.dto.BookDemoFieldDTO;
import com.example.ez.bookdemoform.dto.BookDemoStatsDTO;
import com.example.ez.bookdemoform.dto.BookDemoSubmitRequest;
import com.example.ez.bookdemoform.entity.BookDemoField;
import com.example.ez.bookdemoform.entity.BookDemoSubmission;
import com.example.ez.bookdemoform.service.BookDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book-demo")
@RequiredArgsConstructor
public class BookDemoController {

    private final BookDemoService service;

    // ═══════════════ PUBLIC ENDPOINTS ═══════════════

    /** Get form fields + config for rendering the dialog */
    @GetMapping("/form")
    public ResponseEntity<Map<String, Object>> getForm() {
        return ResponseEntity.ok(Map.of(
                "fields", service.getAllFields(),
                "config", service.getAllConfigs()
        ));
    }

    /** Submit the form */
    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitForm(@RequestBody BookDemoSubmitRequest request) {
        BookDemoSubmission sub = service.submitForm(request);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Demo request submitted successfully!",
                "id", sub.getId()
        ));
    }

    // ═══════════════ ADMIN — FIELDS ═══════════════

    @GetMapping("/admin/fields")
    public ResponseEntity<List<BookDemoField>> adminGetFields() {
        return ResponseEntity.ok(service.getAllFields());
    }

    @PostMapping("/admin/fields")
    public ResponseEntity<BookDemoField> adminCreateField(@RequestBody BookDemoFieldDTO dto) {
        return ResponseEntity.ok(service.createField(dto));
    }

    @PutMapping("/admin/fields/{id}")
    public ResponseEntity<BookDemoField> adminUpdateField(@PathVariable Long id, @RequestBody BookDemoFieldDTO dto) {
        return ResponseEntity.ok(service.updateField(id, dto));
    }

    @DeleteMapping("/admin/fields/{id}")
    public ResponseEntity<Map<String, Object>> adminDeleteField(@PathVariable Long id) {
        service.deleteField(id);
        return ResponseEntity.ok(Map.of("success", true));
    }

    @PutMapping("/admin/fields/reorder")
    public ResponseEntity<Map<String, Object>> adminReorderFields(@RequestBody List<Map<String, Object>> orderList) {
        service.reorderFields(orderList);
        return ResponseEntity.ok(Map.of("success", true));
    }

    // ═══════════════ ADMIN — CONFIG ═══════════════

    @GetMapping("/admin/config")
    public ResponseEntity<Map<String, String>> adminGetConfig() {
        return ResponseEntity.ok(service.getAllConfigs());
    }

    @PutMapping("/admin/config")
    public ResponseEntity<Map<String, Object>> adminUpdateConfig(@RequestBody BookDemoConfigDTO dto) {
        service.updateConfigs(dto.getConfigs());
        return ResponseEntity.ok(Map.of("success", true));
    }

    // ═══════════════ ADMIN — SUBMISSIONS ═══════════════

    @GetMapping("/admin/submissions")
    public ResponseEntity<List<BookDemoSubmission>> adminGetSubmissions(
            @RequestParam(required = false) String status) {
        if (status != null && !status.isEmpty()) {
            return ResponseEntity.ok(service.getSubmissionsByStatus(status));
        }
        return ResponseEntity.ok(service.getAllSubmissions());
    }

    @GetMapping("/admin/submissions/stats")
    public ResponseEntity<BookDemoStatsDTO> adminGetStats() {
        return ResponseEntity.ok(service.getStats());
    }

    @PutMapping("/admin/submissions/{id}/verify")
    public ResponseEntity<BookDemoSubmission> adminVerify(@PathVariable Long id) {
        return ResponseEntity.ok(service.verifySubmission(id));
    }

    @PutMapping("/admin/submissions/{id}/reject")
    public ResponseEntity<BookDemoSubmission> adminReject(@PathVariable Long id) {
        return ResponseEntity.ok(service.rejectSubmission(id));
    }

    @DeleteMapping("/admin/submissions/{id}")
    public ResponseEntity<Map<String, Object>> adminDeleteSubmission(@PathVariable Long id) {
        service.deleteSubmission(id);
        return ResponseEntity.ok(Map.of("success", true));
    }

    // ═══════════════ SEED ═══════════════

    @PostMapping("/admin/seed")
    public ResponseEntity<Map<String, Object>> seed() {
        service.seedDefaultFields();
        return ResponseEntity.ok(Map.of("success", true, "message", "Default fields seeded"));
    }
}