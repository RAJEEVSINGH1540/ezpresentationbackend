package com.example.ez.chatbox.controller;

import com.example.ez.chatbox.dto.ClientMessageRequest;
import com.example.ez.chatbox.dto.MessageDTO;
import com.example.ez.chatbox.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    // Client sends a message from widget
    @PostMapping("/send")
    public ResponseEntity<MessageDTO> send(
            @RequestBody ClientMessageRequest req) {

        if (req.getSessionId() == null || req.getContent() == null
                || req.getContent().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(chatService.clientSendMessage(req));
    }

    // Load chat history for a session (widget reload)
    @GetMapping("/history/{sessionId}")
    public ResponseEntity<List<MessageDTO>> history(
            @PathVariable String sessionId) {
        return ResponseEntity.ok(chatService.getHistory(sessionId));
    }
}