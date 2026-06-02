package com.example.ez.aboutus.controller;

import com.example.ez.aboutus.dto.AboutPageDTO;
import com.example.ez.aboutus.dto.TeamMemberDTO;
import com.example.ez.aboutus.service.AboutPageService;
import com.example.ez.services.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/about")
@RequiredArgsConstructor
public class AboutPageController {

    private final AboutPageService aboutPageService;

    // ─── PUBLIC ENDPOINTS ────────────────────────────────────────────────────

    @GetMapping("/active")
    public ResponseEntity<ApiResponse<AboutPageDTO>> getActivePage() {
        try {
            AboutPageDTO dto = aboutPageService.getActiveAboutPage();
            return ResponseEntity.ok(ApiResponse.success( "About page fetched successfully",dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // ─── CMS ENDPOINTS ───────────────────────────────────────────────────────

    @GetMapping
    public ResponseEntity<ApiResponse<List<AboutPageDTO>>> getAllPages() {
        return ResponseEntity.ok(
            ApiResponse.success( "Fetched all about pages",aboutPageService.getAllAboutPages())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AboutPageDTO>> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                ApiResponse.success( "Fetched",aboutPageService.getAboutPageById(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AboutPageDTO>> create(
            @RequestBody AboutPageDTO dto) {
        try {
            AboutPageDTO created = aboutPageService.createAboutPage(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success( "About page created",created));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AboutPageDTO>> update(
            @PathVariable Long id,
            @RequestBody AboutPageDTO dto) {
        try {
            AboutPageDTO updated = aboutPageService.updateAboutPage(id, dto);
            return ResponseEntity.ok(ApiResponse.success( "About page updated",updated));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        try {
            aboutPageService.deleteAboutPage(id);
            return ResponseEntity.ok(ApiResponse.success(null, "About page deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // ─── IMAGE UPLOAD ────────────────────────────────────────────────────────

    @PostMapping("/upload-image")
    public ResponseEntity<ApiResponse<String>> uploadImage(
            @RequestParam("file") MultipartFile file) {
        try {
            String url = aboutPageService.uploadImage(file);
            return ResponseEntity.ok(ApiResponse.success(url, "Image uploaded"));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Image upload failed: " + e.getMessage()));
        }
    }

    // ─── TEAM MEMBER ENDPOINTS ───────────────────────────────────────────────

    @PostMapping("/{pageId}/team")
    public ResponseEntity<ApiResponse<TeamMemberDTO>> addTeamMember(
            @PathVariable Long pageId,
            @RequestBody TeamMemberDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Team member added",aboutPageService.addTeamMember(pageId, dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/team/{memberId}")
    public ResponseEntity<ApiResponse<TeamMemberDTO>> updateTeamMember(
            @PathVariable Long memberId,
            @RequestBody TeamMemberDTO dto) {
        try {
            return ResponseEntity.ok(
                ApiResponse.success( "Updated",aboutPageService.updateTeamMember(memberId, dto)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/team/{memberId}")
    public ResponseEntity<ApiResponse<String>> deleteTeamMember(@PathVariable Long memberId) {
        try {
            aboutPageService.deleteTeamMember(memberId);
            return ResponseEntity.ok(ApiResponse.success(null, "Deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}