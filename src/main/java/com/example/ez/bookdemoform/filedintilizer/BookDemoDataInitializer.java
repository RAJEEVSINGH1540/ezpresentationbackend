package com.example.ez.bookdemoform.filedintilizer;

import com.example.ez.bookdemoform.entity.BookDemoConfig;
import com.example.ez.bookdemoform.entity.BookDemoField;
import com.example.ez.bookdemoform.repository.BookDemoConfigRepository;
import com.example.ez.bookdemoform.repository.BookDemoFieldRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(1)
@RequiredArgsConstructor
@Slf4j
public class BookDemoDataInitializer implements CommandLineRunner {

    private final BookDemoFieldRepository fieldRepo;
    private final BookDemoConfigRepository configRepo;

    @Override
    @Transactional
    public void run(String... args) {
        seedFields();
        seedConfig();
    }

    private void seedFields() {
        if (fieldRepo.count() > 0) {
            log.info("BookDemo fields already exist — skipping seed ({} fields found)", fieldRepo.count());
            return;
        }

        log.info("Seeding default BookDemo form fields...");

        List<BookDemoField> defaults = List.of(

                // 1. Your name (split into first + last)
                BookDemoField.builder()
                        .fieldName("your_name")
                        .label("Your name")
                        .fieldType("SPLIT_TEXT")
                        .placeholder("First name")
                        .secondPlaceholder("Last name")
                        .secondLabel("")
                        .required(true)
                        .sortOrder(1)
                        .isOptional(false)
                        .build(),

                // 2. Company name
                BookDemoField.builder()
                        .fieldName("company_name")
                        .label("Company name")
                        .fieldType("TEXT")
                        .placeholder("Enter company name")
                        .required(true)
                        .sortOrder(2)
                        .isOptional(false)
                        .build(),

                // 3. Work email
                BookDemoField.builder()
                        .fieldName("work_email")
                        .label("Work email")
                        .fieldType("EMAIL")
                        .placeholder("Example: name@company.com")
                        .required(true)
                        .sortOrder(3)
                        .isOptional(false)
                        .build(),

                // 4. Country or region
                BookDemoField.builder()
                        .fieldName("country")
                        .label("Country or region")
                        .fieldType("SELECT")
                        .placeholder("Select country")
                        .options("[\"India\",\"United States\",\"United Kingdom\",\"Canada\",\"Australia\",\"Germany\",\"France\",\"UAE\",\"Singapore\",\"Japan\",\"South Korea\",\"Brazil\",\"Mexico\",\"South Africa\",\"Netherlands\",\"Sweden\",\"Switzerland\",\"Italy\",\"Spain\",\"New Zealand\",\"Other\"]")
                        .required(true)
                        .sortOrder(4)
                        .isOptional(false)
                        .build(),

                // 5. Phone number (optional)
                BookDemoField.builder()
                        .fieldName("phone")
                        .label("Phone number")
                        .fieldType("PHONE")
                        .placeholder("(123) 456 7890")
                        .required(false)
                        .sortOrder(5)
                        .isOptional(true)
                        .build(),

                // 6. Additional info (optional)
                BookDemoField.builder()
                        .fieldName("message")
                        .label("Provide more information")
                        .fieldType("TEXTAREA")
                        .placeholder("How is your team plan to use our app for?")
                        .required(false)
                        .sortOrder(6)
                        .isOptional(true)
                        .build()
        );

        fieldRepo.saveAll(defaults);
        log.info("✅ Seeded {} default BookDemo form fields", defaults.size());
    }

    private void seedConfig() {
        if (configRepo.count() > 0) {
            log.info("BookDemo config already exists — skipping seed ({} entries found)", configRepo.count());
            return;
        }

        log.info("Seeding default BookDemo dialog config...");

        // Using LinkedHashMap to preserve insertion order
        Map<String, String> defaultConfigs = new LinkedHashMap<>();

        defaultConfigs.put("badge_text",
                "EZ CONSTRUCTION ERP");

        defaultConfigs.put("dialog_title",
                "Experience the super powers of EZ Construction");

        defaultConfigs.put("dialog_subtitle",
                "Schedule a customized tour of our platform to experience firsthand our application. "
                + "Please provide us with some information about your needs and we'll be in touch shortly "
                + "to schedule a personalized demo just for you.");

        defaultConfigs.put("bullet_1",
                "Tailored demonstration of the EZ Construction platform.");

        defaultConfigs.put("bullet_2",
                "Comprehensive exploration of product functionalities tailored to your specific use case.");

        defaultConfigs.put("bullet_3",
                "Interactive session with our experts addressing your requirements.");

        defaultConfigs.put("bullet_4",
                "Discover the ideal plan for your team's needs.");

        defaultConfigs.put("privacy_text",
                "(*) We use the information you provide to us to contact you about our products and services. "
                + "You can unsubscribe from these communications anytime. Please refer to our privacy policy "
                + "for information on our privacy practices.");

        defaultConfigs.put("submit_button_text",
                "Book a demo");

        defaultConfigs.put("secondary_button_text",
                "Live support");

        defaultConfigs.forEach((key, value) -> {
            configRepo.save(
                    BookDemoConfig.builder()
                            .configKey(key)
                            .configValue(value)
                            .build()
            );
        });

        log.info("✅ Seeded {} default BookDemo config entries", defaultConfigs.size());
    }
}