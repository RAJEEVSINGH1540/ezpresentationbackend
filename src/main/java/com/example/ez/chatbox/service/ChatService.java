package com.example.ez.chatbox.service;

import com.example.ez.chatbox.dto.AdminReplyRequest;
import com.example.ez.chatbox.dto.ClientMessageRequest;
import com.example.ez.chatbox.dto.MessageDTO;
import com.example.ez.chatbox.entity.Message;
import com.example.ez.chatbox.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    // ── Client sends message ──────────────────────────────────────────────────
    @Transactional
    public MessageDTO clientSendMessage(ClientMessageRequest req) {

        Message message = Message.builder()
            .sessionId(req.getSessionId())
            .clientName(req.getClientName())
            .content(req.getContent())
            .senderType("CLIENT")
            .status("SENT")
            .build();

        Message saved = messageRepository.save(message);
        log.info("Client [{}] sent: {}", req.getSessionId(), req.getContent());

        MessageDTO dto = toDTO(saved);

        // 1. Push to client's own widget (so they see their message confirmed)
        messagingTemplate.convertAndSend(
            "/topic/chat/" + req.getSessionId(), dto
        );

        // 2. Push to admin panel - update conversation list
        messagingTemplate.convertAndSend(
            "/topic/admin/conversations", dto
        );

        // 3. Push to admin panel - alert for new message
        messagingTemplate.convertAndSend(
            "/topic/admin/new-message", dto
        );

        return dto;
    }

    // ── Admin replies to a specific client ───────────────────────────────────
    @Transactional
    public MessageDTO adminReply(AdminReplyRequest req) {

        // Find client name from previous messages
        List<Message> history = messageRepository
            .findBySessionIdOrderByTimestampAsc(req.getSessionId());

        String clientName = history.stream()
            .filter(m -> "CLIENT".equals(m.getSenderType()))
            .map(Message::getClientName)
            .filter(n -> n != null && !n.isBlank())
            .findFirst()
            .orElse("Client");

        Message message = Message.builder()
            .sessionId(req.getSessionId())
            .clientName(clientName)
            .content(req.getContent())
            .senderType("ADMIN")
            .status("SENT")
            .build();

        Message saved = messageRepository.save(message);
        log.info("Admin replied to [{}]: {}", req.getSessionId(), req.getContent());

        MessageDTO dto = toDTO(saved);

        // 1. Push to specific client's widget → client sees reply LIVE
        messagingTemplate.convertAndSend(
            "/topic/chat/" + req.getSessionId(), dto
        );

        // 2. Push to admin panel → admin sees their own reply in chat
        messagingTemplate.convertAndSend(
            "/topic/admin/conversations", dto
        );

        return dto;
    }

    // ── Get all conversations (latest msg per session) ────────────────────────
    public List<MessageDTO> getAllConversations() {
        return messageRepository
            .findLatestMessagePerSession()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    // ── Get full chat history for a session ───────────────────────────────────
    public List<MessageDTO> getHistory(String sessionId) {
        return messageRepository
            .findBySessionIdOrderByTimestampAsc(sessionId)
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    // ── Mark messages as read ─────────────────────────────────────────────────
    @Transactional
    public void markAsRead(String sessionId) {
        List<Message> msgs = messageRepository
            .findBySessionIdOrderByTimestampAsc(sessionId);

        msgs.stream()
            .filter(m -> "CLIENT".equals(m.getSenderType())
                && !"READ".equals(m.getStatus()))
            .forEach(m -> m.setStatus("READ"));

        messageRepository.saveAll(msgs);
        log.info("Marked messages as read for session: {}", sessionId);
    }

    // ── Helper ────────────────────────────────────────────────────────────────
    private MessageDTO toDTO(Message m) {
        return MessageDTO.builder()
            .id(m.getId())
            .sessionId(m.getSessionId())
            .clientName(m.getClientName())
            .content(m.getContent())
            .senderType(m.getSenderType())
            .status(m.getStatus())
            .timestamp(m.getTimestamp())
            .build();
    }
}