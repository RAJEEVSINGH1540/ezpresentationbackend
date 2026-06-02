package com.example.ez.aboutus.service;

import com.example.ez.aboutus.dto.AboutFeatureDTO;
import com.example.ez.aboutus.dto.AboutPageDTO;
import com.example.ez.aboutus.dto.TeamMemberDTO;
import com.example.ez.aboutus.entity.AboutFeature;
import com.example.ez.aboutus.entity.AboutPage;
import com.example.ez.aboutus.entity.TeamMember;
import com.example.ez.aboutus.repository.AboutFeatureRepository;
import com.example.ez.aboutus.repository.AboutPageRepository;
import com.example.ez.aboutus.repository.TeamMemberRepository;
import com.example.ez.services.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AboutPageService {

    private final AboutPageRepository aboutPageRepository;
    private final AboutFeatureRepository aboutFeatureRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final ImageUploadService imageUploadService;

    // ─── PUBLIC: Get active about page ───────────────────────────────────────

    public AboutPageDTO getActiveAboutPage() {
        AboutPage page = aboutPageRepository.findFirstByIsActiveTrue()
                .orElseThrow(() -> new RuntimeException("No active About page found"));
        return mapToDTO(page, true);
    }

    // ─── CMS: Get all about pages ─────────────────────────────────────────────

    public List<AboutPageDTO> getAllAboutPages() {
        return aboutPageRepository.findAll()
                .stream()
                .map(p -> mapToDTO(p, false))
                .collect(Collectors.toList());
    }

    // ─── CMS: Get by ID ───────────────────────────────────────────────────────

    public AboutPageDTO getAboutPageById(Long id) {
        AboutPage page = aboutPageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("About page not found"));
        return mapToDTO(page, true);
    }

    // ─── CMS: Create ──────────────────────────────────────────────────────────

    @Transactional
    public AboutPageDTO createAboutPage(AboutPageDTO dto) {
        AboutPage page = mapToEntity(dto);
        page = aboutPageRepository.save(page);

        Long pageId = page.getId();
        saveFeatures(dto.getCompanyInfoFeatures(), pageId, "COMPANY_INFO");
        saveFeatures(dto.getSolutionsFeatures(), pageId, "SOLUTIONS");
        saveTeamMembers(dto.getTeamMembers(), pageId);

        return mapToDTO(page, true);
    }

    // ─── CMS: Update ──────────────────────────────────────────────────────────

    @Transactional
    public AboutPageDTO updateAboutPage(Long id, AboutPageDTO dto) {
        AboutPage existing = aboutPageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("About page not found"));

        updateEntityFromDTO(existing, dto);
        existing = aboutPageRepository.save(existing);

        // Replace features and team
        aboutFeatureRepository.deleteByAboutPageId(id);
        teamMemberRepository.deleteByAboutPageId(id);

        saveFeatures(dto.getCompanyInfoFeatures(), id, "COMPANY_INFO");
        saveFeatures(dto.getSolutionsFeatures(), id, "SOLUTIONS");
        saveTeamMembers(dto.getTeamMembers(), id);

        return mapToDTO(existing, true);
    }

    // ─── CMS: Delete ──────────────────────────────────────────────────────────

    @Transactional
    public void deleteAboutPage(Long id) {
        aboutFeatureRepository.deleteByAboutPageId(id);
        teamMemberRepository.deleteByAboutPageId(id);
        aboutPageRepository.deleteById(id);
    }

    // ─── Image Upload ─────────────────────────────────────────────────────────

    public String uploadImage(MultipartFile file) throws IOException {
        return imageUploadService.upload(file);
    }

    // ─── Team Member CRUD ─────────────────────────────────────────────────────

    @Transactional
    public TeamMemberDTO addTeamMember(Long pageId, TeamMemberDTO dto) {
        aboutPageRepository.findById(pageId)
                .orElseThrow(() -> new RuntimeException("About page not found"));

        TeamMember member = TeamMember.builder()
                .aboutPageId(pageId)
                .name(dto.getName())
                .designation(dto.getDesignation())
                .image(dto.getImage())
                .linkedinUrl(dto.getLinkedinUrl())
                .twitterUrl(dto.getTwitterUrl())
                .sortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : 0)
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();

        member = teamMemberRepository.save(member);
        return mapTeamMemberToDTO(member);
    }

    @Transactional
    public TeamMemberDTO updateTeamMember(Long memberId, TeamMemberDTO dto) {
        TeamMember member = teamMemberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Team member not found"));

        member.setName(dto.getName());
        member.setDesignation(dto.getDesignation());
        if (dto.getImage() != null) member.setImage(dto.getImage());
        member.setLinkedinUrl(dto.getLinkedinUrl());
        member.setTwitterUrl(dto.getTwitterUrl());
        member.setSortOrder(dto.getSortOrder());
        if (dto.getIsActive() != null) member.setIsActive(dto.getIsActive());

        member = teamMemberRepository.save(member);
        return mapTeamMemberToDTO(member);
    }

    @Transactional
    public void deleteTeamMember(Long memberId) {
        teamMemberRepository.deleteById(memberId);
    }

    // ─── Helpers ──────────────────────────────────────────────────────────────

    private void saveFeatures(List<AboutFeatureDTO> features, Long pageId, String sectionType) {
        if (features == null) return;
        int order = 0;
        for (AboutFeatureDTO f : features) {
            AboutFeature feature = AboutFeature.builder()
                    .aboutPageId(pageId)
                    .sectionType(sectionType)
                    .iconName(f.getIconName())
                    .title(f.getTitle())
                    .description(f.getDescription())
                    .sortOrder(f.getSortOrder() != null ? f.getSortOrder() : order)
                    .checkItem(f.getCheckItem() != null ? f.getCheckItem() : false)
                    .build();
            aboutFeatureRepository.save(feature);
            order++;
        }
    }

    private void saveTeamMembers(List<TeamMemberDTO> members, Long pageId) {
        if (members == null) return;
        int order = 0;
        for (TeamMemberDTO m : members) {
            TeamMember member = TeamMember.builder()
                    .aboutPageId(pageId)
                    .name(m.getName())
                    .designation(m.getDesignation())
                    .image(m.getImage())
                    .linkedinUrl(m.getLinkedinUrl())
                    .twitterUrl(m.getTwitterUrl())
                    .sortOrder(m.getSortOrder() != null ? m.getSortOrder() : order)
                    .isActive(m.getIsActive() != null ? m.getIsActive() : true)
                    .build();
            teamMemberRepository.save(member);
            order++;
        }
    }

    private AboutPageDTO mapToDTO(AboutPage p, boolean includeRelations) {
        AboutPageDTO dto = AboutPageDTO.builder()
                .id(p.getId())
                .heroTitle(p.getHeroTitle())
                .heroBreadcrumb(p.getHeroBreadcrumb())
                .companyInfoHeading(p.getCompanyInfoHeading())
                .companyInfoDescription(p.getCompanyInfoDescription())
                .companyInfoImage1(p.getCompanyInfoImage1())
                .companyInfoImage2(p.getCompanyInfoImage2())
                .companyInfoImage3(p.getCompanyInfoImage3())
                .companyInfoBtnText(p.getCompanyInfoBtnText())
                .companyInfoBtnLink(p.getCompanyInfoBtnLink())
                .solutionsHeading(p.getSolutionsHeading())
                .solutionsImage1(p.getSolutionsImage1())
                .solutionsImage2(p.getSolutionsImage2())
                .stat1Number(p.getStat1Number())
                .stat1Label(p.getStat1Label())
                .stat2Number(p.getStat2Number())
                .stat2Label(p.getStat2Label())
                .stat3Number(p.getStat3Number())
                .stat3Label(p.getStat3Label())
                .stat4Number(p.getStat4Number())
                .stat4Label(p.getStat4Label())
                .teamHeading(p.getTeamHeading())
                .ctaHeading(p.getCtaHeading())
                .ctaDescription(p.getCtaDescription())
                .ctaBtnText(p.getCtaBtnText())
                .ctaBtnLink(p.getCtaBtnLink())
                .contactHeading(p.getContactHeading())
                .contactDescription(p.getContactDescription())
                .contactAddress(p.getContactAddress())
                .contactEmail(p.getContactEmail())
                .contactPhone(p.getContactPhone())
                .contactHours(p.getContactHours())
                .isActive(p.getIsActive())
                .build();

        if (includeRelations) {
            dto.setCompanyInfoFeatures(
                aboutFeatureRepository
                    .findByAboutPageIdAndSectionTypeOrderBySortOrderAsc(p.getId(), "COMPANY_INFO")
                    .stream().map(this::mapFeatureToDTO).collect(Collectors.toList())
            );
            dto.setSolutionsFeatures(
                aboutFeatureRepository
                    .findByAboutPageIdAndSectionTypeOrderBySortOrderAsc(p.getId(), "SOLUTIONS")
                    .stream().map(this::mapFeatureToDTO).collect(Collectors.toList())
            );
            dto.setTeamMembers(
                teamMemberRepository
                    .findByAboutPageIdOrderBySortOrderAsc(p.getId())
                    .stream().map(this::mapTeamMemberToDTO).collect(Collectors.toList())
            );
        }

        return dto;
    }

    private AboutFeatureDTO mapFeatureToDTO(AboutFeature f) {
        return AboutFeatureDTO.builder()
                .id(f.getId())
                .sectionType(f.getSectionType())
                .iconName(f.getIconName())
                .title(f.getTitle())
                .description(f.getDescription())
                .sortOrder(f.getSortOrder())
                .checkItem(f.getCheckItem())
                .build();
    }

    private TeamMemberDTO mapTeamMemberToDTO(TeamMember m) {
        return TeamMemberDTO.builder()
                .id(m.getId())
                .name(m.getName())
                .designation(m.getDesignation())
                .image(m.getImage())
                .linkedinUrl(m.getLinkedinUrl())
                .twitterUrl(m.getTwitterUrl())
                .sortOrder(m.getSortOrder())
                .isActive(m.getIsActive())
                .build();
    }

    private AboutPage mapToEntity(AboutPageDTO dto) {
        return AboutPage.builder()
                .heroTitle(dto.getHeroTitle())
                .heroBreadcrumb(dto.getHeroBreadcrumb())
                .companyInfoHeading(dto.getCompanyInfoHeading())
                .companyInfoDescription(dto.getCompanyInfoDescription())
                .companyInfoImage1(dto.getCompanyInfoImage1())
                .companyInfoImage2(dto.getCompanyInfoImage2())
                .companyInfoImage3(dto.getCompanyInfoImage3())
                .companyInfoBtnText(dto.getCompanyInfoBtnText())
                .companyInfoBtnLink(dto.getCompanyInfoBtnLink())
                .solutionsHeading(dto.getSolutionsHeading())
                .solutionsImage1(dto.getSolutionsImage1())
                .solutionsImage2(dto.getSolutionsImage2())
                .stat1Number(dto.getStat1Number())
                .stat1Label(dto.getStat1Label())
                .stat2Number(dto.getStat2Number())
                .stat2Label(dto.getStat2Label())
                .stat3Number(dto.getStat3Number())
                .stat3Label(dto.getStat3Label())
                .stat4Number(dto.getStat4Number())
                .stat4Label(dto.getStat4Label())
                .teamHeading(dto.getTeamHeading())
                .ctaHeading(dto.getCtaHeading())
                .ctaDescription(dto.getCtaDescription())
                .ctaBtnText(dto.getCtaBtnText())
                .ctaBtnLink(dto.getCtaBtnLink())
                .contactHeading(dto.getContactHeading())
                .contactDescription(dto.getContactDescription())
                .contactAddress(dto.getContactAddress())
                .contactEmail(dto.getContactEmail())
                .contactPhone(dto.getContactPhone())
                .contactHours(dto.getContactHours())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();
    }

    private void updateEntityFromDTO(AboutPage page, AboutPageDTO dto) {
        page.setHeroTitle(dto.getHeroTitle());
        page.setHeroBreadcrumb(dto.getHeroBreadcrumb());
        page.setCompanyInfoHeading(dto.getCompanyInfoHeading());
        page.setCompanyInfoDescription(dto.getCompanyInfoDescription());
        if (dto.getCompanyInfoImage1() != null) page.setCompanyInfoImage1(dto.getCompanyInfoImage1());
        if (dto.getCompanyInfoImage2() != null) page.setCompanyInfoImage2(dto.getCompanyInfoImage2());
        if (dto.getCompanyInfoImage3() != null) page.setCompanyInfoImage3(dto.getCompanyInfoImage3());
        page.setCompanyInfoBtnText(dto.getCompanyInfoBtnText());
        page.setCompanyInfoBtnLink(dto.getCompanyInfoBtnLink());
        page.setSolutionsHeading(dto.getSolutionsHeading());
        if (dto.getSolutionsImage1() != null) page.setSolutionsImage1(dto.getSolutionsImage1());
        if (dto.getSolutionsImage2() != null) page.setSolutionsImage2(dto.getSolutionsImage2());
        page.setStat1Number(dto.getStat1Number());
        page.setStat1Label(dto.getStat1Label());
        page.setStat2Number(dto.getStat2Number());
        page.setStat2Label(dto.getStat2Label());
        page.setStat3Number(dto.getStat3Number());
        page.setStat3Label(dto.getStat3Label());
        page.setStat4Number(dto.getStat4Number());
        page.setStat4Label(dto.getStat4Label());
        page.setTeamHeading(dto.getTeamHeading());
        page.setCtaHeading(dto.getCtaHeading());
        page.setCtaDescription(dto.getCtaDescription());
        page.setCtaBtnText(dto.getCtaBtnText());
        page.setCtaBtnLink(dto.getCtaBtnLink());
        page.setContactHeading(dto.getContactHeading());
        page.setContactDescription(dto.getContactDescription());
        page.setContactAddress(dto.getContactAddress());
        page.setContactEmail(dto.getContactEmail());
        page.setContactPhone(dto.getContactPhone());
        page.setContactHours(dto.getContactHours());
        if (dto.getIsActive() != null) page.setIsActive(dto.getIsActive());
    }
}