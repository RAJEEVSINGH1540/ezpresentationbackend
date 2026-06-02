package com.example.ez.intilizer.homepage;

import com.example.ez.homepage.trusted.entity.TrustedBrand;
import com.example.ez.homepage.trusted.entity.TrustedSection;
import com.example.ez.homepage.trusted.repository.TrustedBrandRepository;
import com.example.ez.homepage.trusted.repository.TrustedSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
@RequiredArgsConstructor
public class TrustedBrandsInitializer implements CommandLineRunner {

    private final TrustedSectionRepository sectionRepo;
    private final TrustedBrandRepository brandRepo;

    @Override
    public void run(String... args) {
        if (sectionRepo.count() == 0) {
            sectionRepo.save(TrustedSection.builder()
                    .illustrationUrl("/uploads/dashboard.png")
                    .mainTagline("EZ Construction's Real Estate & Construction ERP Software is a complete, integrated platform that automates all critical business processes")
                    .subTagline("— helping builders, developers, contractors, construction and infrastructure companies manage projects, sales, and operations seamlessly.")
                    .trustedCountPrefix("Over")
                    .trustedCountValue("500+")
                    .trustedCountSuffix("Brands have Trusted us")
                    .build());
        }
        if (brandRepo.count() == 0) {
            String[][] brands = {
                {"BuildCore","https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?w=120&h=60&fit=crop"},
                {"StructPro","https://images.unsplash.com/photo-1504307651254-35680f356dfd?w=120&h=60&fit=crop"},
                {"SiteMaster","https://images.unsplash.com/photo-1590644365607-39c57e30dd75?w=120&h=60&fit=crop"},
                {"FoundationX","https://images.unsplash.com/photo-1498603993951-8a027a8a8f84?w=120&h=60&fit=crop"},
                {"CivilTech","https://images.unsplash.com/photo-1541888946425-d81bb19240f5?w=120&h=60&fit=crop"},
                {"PrimeBuild","https://images.unsplash.com/photo-1477959858617-67f85cf4f1df?w=120&h=60&fit=crop"},
                {"ArchiPlan","https://images.unsplash.com/photo-1467533003447-e295ff1b0435?w=120&h=60&fit=crop"},
                {"GroundWork","https://images.unsplash.com/photo-1565008447742-97f6f38c985c?w=120&h=60&fit=crop"},
                {"SteelFrame","https://images.unsplash.com/photo-1519389950473-47ba0277781c?w=120&h=60&fit=crop"},
            };
            for (int i = 0; i < brands.length; i++) {
                brandRepo.save(TrustedBrand.builder()
                        .sortOrder(i + 1).name(brands[i][0])
                        .logoUrl(brands[i][1]).active(true).build());
            }
        }
    }
}