package com.example.ez.homepage.industry.controller;

import com.example.ez.homepage.industry.dto.IndustryServiceDto;
import com.example.ez.homepage.industry.service.IndustryServiceCmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/homepage/industry-solutions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class IndustryServiceController {

    private final IndustryServiceCmsService service;

    @GetMapping
    public ResponseEntity<List<IndustryServiceDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<IndustryServiceDto>> findActive() {
        return ResponseEntity.ok(service.findActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndustryServiceDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<IndustryServiceDto> create(
            @RequestBody IndustryServiceDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IndustryServiceDto> update(
            @PathVariable Long id,
            @RequestBody IndustryServiceDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<IndustryServiceDto> uploadImage(
            @PathVariable Long id,
            @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadImage(id, file));
    }
}