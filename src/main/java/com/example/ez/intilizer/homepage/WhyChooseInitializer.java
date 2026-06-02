package com.example.ez.intilizer.homepage;

import com.example.ez.homepage.whychoose.entity.WhyChooseBenefit;
import com.example.ez.homepage.whychoose.entity.WhyChooseSection;
import com.example.ez.homepage.whychoose.repository.WhyChooseBenefitRepository;
import com.example.ez.homepage.whychoose.repository.WhyChooseSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
@RequiredArgsConstructor
public class WhyChooseInitializer implements CommandLineRunner {

    private final WhyChooseSectionRepository sectionRepo;
    private final WhyChooseBenefitRepository benefitRepo;

    @Override
    public void run(String... args) {
        if (sectionRepo.count() == 0) {
            sectionRepo.save(WhyChooseSection.builder()
                    .eyebrowText("OUR BENEFITS ↘")
                    .headline("Why choose us")
                    .dashboardImageUrl("/uploads/dashboard.avif")
                    .build());
        }
        if (benefitRepo.count() == 0) {
            benefitRepo.saveAll(List.of(
                WhyChooseBenefit.builder().sortOrder(1).numLabel("01")
                    .title("END-TO-END ERP PLATFORM")
                    .description("From procurement to payroll — EZ Construction unifies every business process into one intelligent platform, eliminating silos and manual errors.")
                    .build(),
                WhyChooseBenefit.builder().sortOrder(2).numLabel("02")
                    .title("REAL-TIME PROJECT VISIBILITY")
                    .description("Monitor every project's cost, progress, and resource usage live. Make data-driven decisions before issues become overruns.")
                    .build(),
                WhyChooseBenefit.builder().sortOrder(3).numLabel("03")
                    .title("INDUSTRY-BUILT WORKFLOWS")
                    .description("Not generic software adapted for construction — purpose-engineered workflows for builders, contractors, and developers from day one.")
                    .build(),
                WhyChooseBenefit.builder().sortOrder(4).numLabel("04")
                    .title("ENTERPRISE-GRADE SECURITY")
                    .description("Role-based access, audit trails, and encrypted data storage ensure your sensitive project and financial data stays protected.")
                    .build()
            ));
        }
    }
}