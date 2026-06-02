package com.example.ez.chatbox.repository;

import com.example.ez.chatbox.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // All messages for one session (chat history)
    List<Message> findBySessionIdOrderByTimestampAsc(String sessionId);

    // Latest message per session (for conversation list in admin)
    @Query(value =
        "SELECT * FROM messages m WHERE m.timestamp = " +
        "(SELECT MAX(m2.timestamp) FROM messages m2 " +
        " WHERE m2.session_id = m.session_id) " +
        "ORDER BY m.timestamp DESC",
        nativeQuery = true)
    List<Message> findLatestMessagePerSession();

    // Count unread messages from clients for admin
    long countBySessionIdAndSenderTypeAndStatus(
        String sessionId, String senderType, String status
    );
}