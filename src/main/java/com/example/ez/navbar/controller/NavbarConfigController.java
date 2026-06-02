package com.example.ez.navbar.controller;

import com.example.ez.navbar.dto.NavbarConfigDto;
import com.example.ez.navbar.services.NavbarConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/homepage/navbar")
@RequiredArgsConstructor
public class NavbarConfigController {

    private final NavbarConfigService service;

    // GET active config (used by frontend navbar)
    @GetMapping("/active")
    public ResponseEntity<NavbarConfigDto> getActive() {
        return ResponseEntity.ok(service.getActive());
    }

    // GET all (used by CMS)
    @GetMapping
    public ResponseEntity<List<NavbarConfigDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // POST create
    @PostMapping
    public ResponseEntity<NavbarConfigDto> create(@RequestBody NavbarConfigDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // PUT update
    @PutMapping("/{id}")
    public ResponseEntity<NavbarConfigDto> update(
        @PathVariable Long id,
        @RequestBody NavbarConfigDto dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // POST upload logo
    @PostMapping("/{id}/upload-logo")
    public ResponseEntity<NavbarConfigDto> uploadLogo(
        @PathVariable Long id,
        @RequestParam("file") MultipartFile file
    ) throws Exception {
        return ResponseEntity.ok(service.uploadLogo(id, file));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}