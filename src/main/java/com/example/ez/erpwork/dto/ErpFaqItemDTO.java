package com.example.ez.erpwork.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpFaqItemDTO {
    private Long   id;
    private Long   erpServiceId;
    private String question;
    private String answer;
    private int    displayOrder;
    private String faqColumn;           // ← was "column"
    private String sectionBadgeText;
    private String sectionTitle;
    private String sectionDescription;
}