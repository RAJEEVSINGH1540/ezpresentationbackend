package com.example.ez.chatbox.dto;

import lombok.Data;

@Data
public class ClientMessageRequest {
    private String sessionId;
    private String clientName;
    private String content;
}