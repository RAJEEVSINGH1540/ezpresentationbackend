package com.example.ez.bookdemoform.service;

import com.example.ez.bookdemoform.dto.BookDemoFieldDTO;
import com.example.ez.bookdemoform.dto.BookDemoStatsDTO;
import com.example.ez.bookdemoform.dto.BookDemoSubmitRequest;
import com.example.ez.bookdemoform.entity.BookDemoConfig;
import com.example.ez.bookdemoform.entity.BookDemoField;
import com.example.ez.bookdemoform.entity.BookDemoSubmission;
import com.example.ez.bookdemoform.repository.BookDemoConfigRepository;
import com.example.ez.bookdemoform.repository.BookDemoFieldRepository;
import com.example.ez.bookdemoform.repository.BookDemoSubmissionRepository;
import com.example.ez.email.EmailService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookDemoService {

    private final BookDemoFieldRepository fieldRepo;
    private final BookDemoSubmissionRepository submissionRepo;
    private final BookDemoConfigRepository configRepo;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    // ═══════════════════ FIELDS ═══════════════════

    public List<BookDemoField> getAllFields() {
        return fieldRepo.findAllByOrderBySortOrderAsc();
    }

    public BookDemoField createField(BookDemoFieldDTO dto) {
        BookDemoField field = BookDemoField.builder()
                .fieldName(dto.getFieldName())
                .label(dto.getLabel())
                .fieldType(dto.getFieldType())
                .placeholder(dto.getPlaceholder())
                .options(dto.getOptions())
                .required(dto.getRequired() != null ? dto.getRequired() : true)
                .sortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : 999)
                .isOptional(dto.getIsOptional() != null ? dto.getIsOptional() : false)
                .secondPlaceholder(dto.getSecondPlaceholder())
                .secondLabel(dto.getSecondLabel())
                .build();
        return fieldRepo.save(field);
    }

    public BookDemoField updateField(Long id, BookDemoFieldDTO dto) {
        BookDemoField field = fieldRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Field not found: " + id));

        if (dto.getFieldName() != null) field.setFieldName(dto.getFieldName());
        if (dto.getLabel() != null) field.setLabel(dto.getLabel());
        if (dto.getFieldType() != null) field.setFieldType(dto.getFieldType());
        if (dto.getPlaceholder() != null) field.setPlaceholder(dto.getPlaceholder());
        if (dto.getOptions() != null) field.setOptions(dto.getOptions());
        if (dto.getRequired() != null) field.setRequired(dto.getRequired());
        if (dto.getSortOrder() != null) field.setSortOrder(dto.getSortOrder());
        if (dto.getIsOptional() != null) field.setIsOptional(dto.getIsOptional());
        if (dto.getSecondPlaceholder() != null) field.setSecondPlaceholder(dto.getSecondPlaceholder());
        if (dto.getSecondLabel() != null) field.setSecondLabel(dto.getSecondLabel());

        return fieldRepo.save(field);
    }

    public void deleteField(Long id) {
        fieldRepo.deleteById(id);
    }

    public void reorderFields(List<Map<String, Object>> orderList) {
        for (Map<String, Object> item : orderList) {
            Long id = Long.valueOf(item.get("id").toString());
            Integer order = Integer.valueOf(item.get("sortOrder").toString());
            fieldRepo.findById(id).ifPresent(f -> {
                f.setSortOrder(order);
                fieldRepo.save(f);
            });
        }
    }

    // ═══════════════════ CONFIG ═══════════════════

    public Map<String, String> getAllConfigs() {
        return configRepo.findAll().stream()
                .collect(Collectors.toMap(BookDemoConfig::getConfigKey, BookDemoConfig::getConfigValue));
    }

    public void updateConfigs(Map<String, String> configs) {
        configs.forEach((key, value) -> {
            BookDemoConfig config = configRepo.findByConfigKey(key)
                    .orElse(BookDemoConfig.builder().configKey(key).configValue(value).build());
            config.setConfigValue(value);
            configRepo.save(config);
        });
    }

    // ═══════════════════ SUBMISSIONS ═══════════════════

    @Transactional
    public BookDemoSubmission submitForm(BookDemoSubmitRequest request) {
        try {
            String formDataJson = objectMapper.writeValueAsString(request.getFormData());

            // Extract email and name from form data
            String email = request.getFormData().getOrDefault("work_email",
                    request.getFormData().getOrDefault("email", ""));
            String name = request.getFormData().getOrDefault("your_name",
                    request.getFormData().getOrDefault("first_name",
                            request.getFormData().getOrDefault("name", "")));

            BookDemoSubmission submission = BookDemoSubmission.builder()
                    .formData(formDataJson)
                    .email(email)
                    .name(name)
                    .status("PENDING")
                    .build();

            submission = submissionRepo.save(submission);

            // Send confirmation email
            if (!email.isEmpty()) {
                String displayName = name.isEmpty() ? "there" : name;
                String html = emailService.buildSubmissionConfirmationEmail(displayName);
                emailService.sendEmail(email, "Demo Request Received — EZ Construction", html);
            }

            return submission;
        } catch (Exception e) {
            log.error("Failed to submit form", e);
            throw new RuntimeException("Failed to submit form", e);
        }
    }

    public List<BookDemoSubmission> getAllSubmissions() {
        return submissionRepo.findAllByOrderBySubmittedAtDesc();
    }

    public List<BookDemoSubmission> getSubmissionsByStatus(String status) {
        return submissionRepo.findByStatusOrderBySubmittedAtDesc(status);
    }

    @Transactional
    public BookDemoSubmission verifySubmission(Long id) {
        BookDemoSubmission sub = submissionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found: " + id));
        sub.setStatus("VERIFIED");
        sub.setVerifiedAt(LocalDateTime.now());
        sub = submissionRepo.save(sub);

        // Send verification email
        if (sub.getEmail() != null && !sub.getEmail().isEmpty()) {
            String displayName = (sub.getName() == null || sub.getName().isEmpty()) ? "there" : sub.getName();
            String html = emailService.buildVerificationEmail(displayName);
            emailService.sendEmail(sub.getEmail(), "Demo Request Verified — EZ Construction ✅", html);
        }

        return sub;
    }

    @Transactional
    public BookDemoSubmission rejectSubmission(Long id) {
        BookDemoSubmission sub = submissionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found: " + id));
        sub.setStatus("REJECTED");
        return submissionRepo.save(sub);
    }

    public void deleteSubmission(Long id) {
        submissionRepo.deleteById(id);
    }

    public BookDemoStatsDTO getStats() {
        return BookDemoStatsDTO.builder()
                .total(submissionRepo.count())
                .pending(submissionRepo.countByStatus("PENDING"))
                .verified(submissionRepo.countByStatus("VERIFIED"))
                .rejected(submissionRepo.countByStatus("REJECTED"))
                .build();
    }

    // Parse formData JSON back to map
    public Map<String, String> parseFormData(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
        } catch (Exception e) {
            return Map.of();
        }
    }

    // ═══════════════════ SEED DEFAULT FIELDS ═══════════════════

    @Transactional
    public void seedDefaultFields() {
        if (fieldRepo.count() > 0) return;

        List<BookDemoField> defaults = List.of(
                BookDemoField.builder().fieldName("your_name").label("Your name").fieldType("SPLIT_TEXT")
                        .placeholder("First name").secondPlaceholder("Last name").secondLabel("")
                        .required(true).sortOrder(1).isOptional(false).build(),
                BookDemoField.builder().fieldName("company_name").label("Company name").fieldType("TEXT")
                        .placeholder("Enter company name").required(true).sortOrder(2).isOptional(false).build(),
                BookDemoField.builder().fieldName("work_email").label("Work email").fieldType("EMAIL")
                        .placeholder("Example: name@company.com").required(true).sortOrder(3).isOptional(false).build(),
                BookDemoField.builder().fieldName("country").label("Country or region").fieldType("SELECT")
                        .placeholder("Select country")
                        .options("[\"India\",\"United States\",\"United Kingdom\",\"Canada\",\"Australia\",\"Germany\",\"France\",\"UAE\",\"Singapore\",\"Other\"]")
                        .required(true).sortOrder(4).isOptional(false).build(),
                BookDemoField.builder().fieldName("phone").label("Phone number").fieldType("PHONE")
                        .placeholder("(123) 456 7890").required(false).sortOrder(5).isOptional(true).build(),
                BookDemoField.builder().fieldName("message").label("Provide more information").fieldType("TEXTAREA")
                        .placeholder("How is your team plan to use our app for?").required(false).sortOrder(6).isOptional(true).build()
        );

        fieldRepo.saveAll(defaults);

        // Seed default configs
        Map<String, String> defaultConfigs = Map.of(
                "badge_text", "EZ CONSTRUCTION ERP",
                "dialog_title", "Experience the super powers of EZ Construction",
                "dialog_subtitle", "Schedule a customized tour of our platform to experience firsthand our application. Please provide us with some information about your needs and we'll be in touch shortly to schedule a personalized demo just for you.",
                "bullet_1", "Tailored demonstration of the EZ Construction platform.",
                "bullet_2", "Comprehensive exploration of product functionalities tailored to your specific use case.",
                "bullet_3", "Interactive session with our experts addressing your requirements.",
                "bullet_4", "Discover the ideal plan for your team's needs.",
                "privacy_text", "(*) We use the information you provide to us to contact you about our products and services. You can unsubscribe from these communications anytime. Please refer to our privacy policy for information on our privacy practices.",
                "submit_button_text", "Book a demo",
                "secondary_button_text", "Live support"
        );

        defaultConfigs.forEach((key, value) -> {
            configRepo.save(BookDemoConfig.builder().configKey(key).configValue(value).build());
        });
    }
}