package dev.bogdanbalalau.chatapp.service;

import dev.bogdanbalalau.chatapp.dto.MessageDTO;
import dev.bogdanbalalau.chatapp.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    Message saveMessage(MessageDTO messageDTO);
    List<Message> findBySenderIdAndRecipientId(UUID senderId, UUID recipientId);
}
