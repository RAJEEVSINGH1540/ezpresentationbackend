package com.example.ez.intilizer;

import com.example.ez.aboutus.entity.AboutFeature;
import com.example.ez.aboutus.entity.AboutPage;
import com.example.ez.aboutus.entity.TeamMember;
import com.example.ez.aboutus.repository.AboutFeatureRepository;
import com.example.ez.aboutus.repository.AboutPageRepository;
import com.example.ez.aboutus.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AboutDataInitializer implements CommandLineRunner {

    private final AboutPageRepository aboutPageRepository;
    private final AboutFeatureRepository aboutFeatureRepository;
    private final TeamMemberRepository teamMemberRepository;

    @Override
    @Transactional
    public void run(String... args) {

        // Only seed if no about page exists
        if (aboutPageRepository.count() > 0) {
            log.info("About page data already exists — skipping seed.");
            return;
        }

        log.info("Seeding About Page data...");

        // ── 1. Create the About Page ───────────────────────────────────
        AboutPage page = AboutPage.builder()
            .heroTitle("About Us")
            .heroBreadcrumb("About Us")

            // Company Info
            .companyInfoHeading("Exclusive technology to provide IT solutions")
            .companyInfoDescription(
                "We are a leading IT company specializing in ERP solutions and " +
                "enterprise software. With a passionate team of 200+ experts, " +
                "we have served 1800+ happy clients across industries, delivering " +
                "scalable, reliable, and custom software products that truly " +
                "transform businesses.")
            .companyInfoImage1("https://images.unsplash.com/photo-1522071820081-009f0129c71c?w=400")
            .companyInfoImage2("https://images.unsplash.com/photo-1551434678-e076c223a692?w=400")
            .companyInfoImage3("https://images.unsplash.com/photo-1600880292203-757bb62b4baf?w=400")
            .companyInfoBtnText("More About Us")
            .companyInfoBtnLink("/contact")

            // Solutions
            .solutionsHeading("Providing IT solutions & services for startups")
            .solutionsImage1("https://images.unsplash.com/photo-1556761175-4b46a572b786?w=500")
            .solutionsImage2("https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=400")

            // Stats
            .stat1Number("1800+").stat1Label("Happy Clients")
            .stat2Number("600+").stat2Label("Finished Projects")
            .stat3Number("200+").stat3Label("Skilled Experts")
            .stat4Number("98%").stat4Label("Client Satisfaction")

            // Team
            .teamHeading("Our expert team is always ready to help you")

            // CTA
            .ctaHeading("Let's build an awesome project together")
            .ctaDescription("Feel free to contact us — we don't spam your email")
            .ctaBtnText("Let's Start a Project")
            .ctaBtnLink("/contact")

            // Contact
            .contactHeading("Fill The Contact Form")
            .contactDescription("We would love to hear from you. Send us a message and we'll respond as soon as possible.")
            .contactAddress("1791 Yorkshire Circle\nNew York, NY 10001")
            .contactEmail("info@eztech.com")
            .contactPhone("+1 518-564-3200")
            .contactHours("Mon – Sat: 8:00 AM – 10:00 PM")

            .isActive(true)
            .build();

        page = aboutPageRepository.save(page);
        final Long pageId = page.getId();
        log.info("Created About Page with ID: {}", pageId);

        // ── 2. Company Info checkmark features ───────────────────────
        List<AboutFeature> companyFeatures = List.of(
            AboutFeature.builder()
                .aboutPageId(pageId)
                .sectionType("COMPANY_INFO")
                .title("Easily Build Custom Reports And Dashboards")
                .checkItem(true).sortOrder(0).build(),

            AboutFeature.builder()
                .aboutPageId(pageId)
                .sectionType("COMPANY_INFO")
                .title("Legacy Software Modernization")
                .checkItem(true).sortOrder(1).build(),

            AboutFeature.builder()
                .aboutPageId(pageId)
                .sectionType("COMPANY_INFO")
                .title("Software For The Open Enterprise")
                .checkItem(true).sortOrder(2).build()
        );
        aboutFeatureRepository.saveAll(companyFeatures);
        log.info("Seeded {} company info features.", companyFeatures.size());

        // ── 3. Solutions features ─────────────────────────────────────
        List<AboutFeature> solutionFeatures = List.of(
            AboutFeature.builder()
                .aboutPageId(pageId)
                .sectionType("SOLUTIONS")
                .iconName("trophy")
                .title("Quality Solution for Business")
                .description(
                    "We deliver enterprise-grade ERP systems and software solutions " +
                    "customized to your exact business requirements.")
                .checkItem(false).sortOrder(0).build(),

            AboutFeature.builder()
                .aboutPageId(pageId)
                .sectionType("SOLUTIONS")
                .iconName("users")
                .title("Amazing Expert Teams")
                .description(
                    "Our seasoned professionals bring deep domain expertise across " +
                    "industries, technologies, and business verticals.")
                .checkItem(false).sortOrder(1).build(),

            AboutFeature.builder()
                .aboutPageId(pageId)
                .sectionType("SOLUTIONS")
                .iconName("headset")
                .title("Urgent Support For Clients")
                .description(
                    "Round-the-clock dedicated support ensuring your mission-critical " +
                    "systems run without any interruption.")
                .checkItem(false).sortOrder(2).build()
        );
        aboutFeatureRepository.saveAll(solutionFeatures);
        log.info("Seeded {} solutions features.", solutionFeatures.size());

        // ── 4. Team members ───────────────────────────────────────────
        List<TeamMember> team = List.of(
            TeamMember.builder()
                .aboutPageId(pageId)
                .name("Alexander Cameron")
                .designation("Product Designer")
                .image("https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=300")
                .linkedinUrl("https://linkedin.com")
                .twitterUrl("https://twitter.com")
                .sortOrder(0).isActive(true).build(),

            TeamMember.builder()
                .aboutPageId(pageId)
                .name("Zachary Collins")
                .designation("Cyber Specialist")
                .image("https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=300")
                .linkedinUrl("https://linkedin.com")
                .twitterUrl("https://twitter.com")
                .sortOrder(1).isActive(true).build(),

            TeamMember.builder()
                .aboutPageId(pageId)
                .name("Barbara Dundas")
                .designation("Digital Marketer")
                .image("https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=300")
                .linkedinUrl("https://linkedin.com")
                .twitterUrl("https://twitter.com")
                .sortOrder(2).isActive(true).build(),

            TeamMember.builder()
                .aboutPageId(pageId)
                .name("Marvin McKinney")
                .designation("CEO & Founder")
                .image("https://images.unsplash.com/photo-1519085360753-af0119f7cbe7?w=300")
                .linkedinUrl("https://linkedin.com")
                .twitterUrl("https://twitter.com")
                .sortOrder(3).isActive(true).build(),

            TeamMember.builder()
                .aboutPageId(pageId)
                .name("Sophia Rodriguez")
                .designation("Creative Director")
                .image("https://images.unsplash.com/photo-1438761681033-6461ffad8d80?w=300")
                .linkedinUrl("https://linkedin.com")
                .twitterUrl("https://twitter.com")
                .sortOrder(4).isActive(true).build(),

            TeamMember.builder()
                .aboutPageId(pageId)
                .name("James Wilson")
                .designation("Lead Developer")
                .image("https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=300")
                .linkedinUrl("https://linkedin.com")
                .twitterUrl("https://twitter.com")
                .sortOrder(5).isActive(true).build()
        );
        teamMemberRepository.saveAll(team);
        log.info("Seeded {} team members.", team.size());

        log.info("✅  About Page seed complete! Page ID = {}", pageId);
    }
}