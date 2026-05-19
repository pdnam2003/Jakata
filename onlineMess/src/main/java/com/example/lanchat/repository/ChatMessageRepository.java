package com.example.lanchat.repository;

import com.example.lanchat.model.ChatMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("SELECT m FROM ChatMessage m WHERE m.recipient IS NULL OR m.recipient = :username OR m.sender = :username ORDER BY m.timestamp DESC")
    List<ChatMessage> findRecentForUser(@Param("username") String username, Pageable pageable);
}
