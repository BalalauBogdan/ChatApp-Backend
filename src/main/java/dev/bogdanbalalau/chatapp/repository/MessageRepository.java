package dev.bogdanbalalau.chatapp.repository;

import dev.bogdanbalalau.chatapp.entity.Message;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    @Query("SELECT m FROM Message m WHERE (m.senderId = :userId1 AND m.recipientId = :userId2) " +
            "OR (m.senderId = :userId2 AND m.recipientId = :userId1) ORDER BY m.timeStamp ASC")
    List<Message> findBySenderIdAndRecipientId(@Param("userId1") UUID userId1, @Param("userId2") UUID userId2);
}
