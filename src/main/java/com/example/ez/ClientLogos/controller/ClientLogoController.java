package com.example.ez.ClientLogos.controller;

import com.example.ez.ClientLogos.dto.ClientLogoDTO;
import com.example.ez.ClientLogos.service.ClientLogoService;
import com.example.ez.services.service.ImageUploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClientLogoController {

    private final ClientLogoService service;
    private final ImageUploadService   imageUploadService;

    // ════════════════════════════════════════════════════════════
    //  PUBLIC ENDPOINT
    // ════════════════════════════════════════════════════════════

    @GetMapping("/api/client-logos")
    public ResponseEntity<List<ClientLogoDTO>> getActiveLogos() {
        return ResponseEntity.ok(service.getActiveLogos());
    }

    // ════════════════════════════════════════════════════════════
    //  ADMIN ENDPOINTS
    // ════════════════════════════════════════════════════════════

    @GetMapping("/api/admin/client-logos")
    public ResponseEntity<List<ClientLogoDTO>> getAllLogos() {
        return ResponseEntity.ok(service.getAllLogos());
    }

    @GetMapping("/api/admin/client-logos/{id}")
    public ResponseEntity<ClientLogoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLogoById(id));
    }

    @PostMapping("/api/admin/client-logos")
    public ResponseEntity<ClientLogoDTO> create(
            @Valid @RequestBody ClientLogoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createLogo(dto));
    }

    @PutMapping("/api/admin/client-logos/{id}")
    public ResponseEntity<ClientLogoDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ClientLogoDTO dto) {
        return ResponseEntity.ok(service.updateLogo(id, dto));
    }

    @DeleteMapping("/api/admin/client-logos/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        service.deleteLogo(id);
        return ResponseEntity.ok(Map.of("message", "Client logo deleted successfully"));
    }

    // ── Image Upload ─────────────────────────────────────────────
    @PostMapping(
        value    = "/api/admin/client-logos/upload",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Map<String, String>> uploadLogo(
            @RequestParam("file") MultipartFile file) throws IOException {
        String url = imageUploadService.upload(file);
        return ResponseEntity.ok(Map.of("url", url));
    }
}