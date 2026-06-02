package com.example.ez.chatbox.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique browser session ID - identifies each client
    @Column(nullable = false)
    private String sessionId;

    // Client's display name (entered in welcome form)
    private String clientName;

    // Message text
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    // "CLIENT" = sent by website visitor
    // "ADMIN"  = sent by your team
    @Column(nullable = false)
    private String senderType;

    // "SENT", "DELIVERED", "READ"
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        if (timestamp == null) {
            timestamp = LocalDateTime.now();
        }
        if (status == null) {
            status = "SENT";
        }
    }
}