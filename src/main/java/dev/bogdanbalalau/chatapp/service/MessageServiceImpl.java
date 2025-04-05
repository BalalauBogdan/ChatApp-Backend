package dev.bogdanbalalau.chatapp.service;

import dev.bogdanbalalau.chatapp.dto.MessageDTO;
import dev.bogdanbalalau.chatapp.entity.Message;
import dev.bogdanbalalau.chatapp.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message saveMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setSenderId(messageDTO.getSenderId());
        message.setRecipientId(messageDTO.getRecipientId());
        message.setContent(messageDTO.getContent());
        message.setTimeStamp(LocalDateTime.now());
        return this.messageRepository.save(message);
    }

    @Override
    public List<Message> findBySenderIdAndRecipientId(UUID senderId, UUID recipientId) {
        return this.messageRepository.findBySenderIdAndRecipientId(senderId, recipientId);
    }
}
