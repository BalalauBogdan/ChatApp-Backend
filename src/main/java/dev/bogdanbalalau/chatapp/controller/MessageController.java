package dev.bogdanbalalau.chatapp.controller;

import dev.bogdanbalalau.chatapp.dto.MessageDTO;
import dev.bogdanbalalau.chatapp.service.MessageService;
import dev.bogdanbalalau.chatapp.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/message")
@CrossOrigin
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> saveMessage(@RequestBody MessageDTO messageDTO) {
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Message saved successfully!")
                .data(this.messageService.saveMessage(messageDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{senderId}/{recipientId}")
    public ResponseEntity<ApiResponse> findBySenderIdAndRecipientId(@PathVariable UUID senderId, @PathVariable UUID recipientId) {
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("List with all messages")
                .data(this.messageService.findBySenderIdAndRecipientId(senderId, recipientId))
                .build();
        return ResponseEntity.ok(response);
    }
}
