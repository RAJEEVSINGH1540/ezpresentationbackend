package com.example.ez.chatbox.dto;

import lombok.Data;

@Data
public class AdminReplyRequest {
    private String sessionId;   // Which client to reply to
    private String content;
}