package com.example.ez.chatbox.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
    private Long id;
    private String sessionId;
    private String clientName;
    private String content;
    private String senderType;   // "CLIENT" or "ADMIN"
    private String status;
    private LocalDateTime timestamp;
}