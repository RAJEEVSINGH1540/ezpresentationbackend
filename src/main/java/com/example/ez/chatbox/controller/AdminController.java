package com.example.ez.chatbox.controller;

import com.example.ez.chatbox.dto.AdminReplyRequest;
import com.example.ez.chatbox.dto.MessageDTO;
import com.example.ez.chatbox.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ChatService chatService;

    // Get all conversations for admin panel
    @GetMapping("/conversations")
    public ResponseEntity<List<MessageDTO>> conversations() {
        return ResponseEntity.ok(chatService.getAllConversations());
    }

    // Get full chat history for a specific session
    @GetMapping("/history/{sessionId}")
    public ResponseEntity<List<MessageDTO>> history(
            @PathVariable String sessionId) {
        return ResponseEntity.ok(chatService.getHistory(sessionId));
    }

    // Admin sends reply to a specific client
    @PostMapping("/reply")
    public ResponseEntity<MessageDTO> reply(
            @RequestBody AdminReplyRequest req) {

        if (req.getSessionId() == null || req.getContent() == null
                || req.getContent().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(chatService.adminReply(req));
    }

    // Mark all client messages as read
    @PostMapping("/read/{sessionId}")
    public ResponseEntity<Void> markRead(@PathVariable String sessionId) {
        chatService.markAsRead(sessionId);
        return ResponseEntity.ok().build();
    }
}