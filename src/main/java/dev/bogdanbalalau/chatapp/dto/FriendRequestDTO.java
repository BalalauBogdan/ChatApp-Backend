package dev.bogdanbalalau.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class FriendRequestDTO {
    private UUID id;
    private String senderUsername;
}
