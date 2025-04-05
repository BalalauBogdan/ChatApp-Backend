package dev.bogdanbalalau.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MessageDTO {
    private UUID senderId;
    private UUID recipientId;
    private String content;
}
