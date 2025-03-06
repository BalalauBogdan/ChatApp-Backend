package dev.bogdanbalalau.chatapp.controller;

import dev.bogdanbalalau.chatapp.exception.FriendRequestAlreadySentException;
import dev.bogdanbalalau.chatapp.exception.FriendRequestNotFoundException;
import dev.bogdanbalalau.chatapp.exception.UserNotFoundException;
import dev.bogdanbalalau.chatapp.service.FriendRequestService;
import dev.bogdanbalalau.chatapp.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/friends")
public class FriendRequestController {
    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping("/send-request/{receiverUsername}")
    public ResponseEntity<ApiResponse> sendFriendRequest(@RequestParam String senderUsername, @PathVariable String receiverUsername) {
        try {
            ApiResponse response = ApiResponse.builder()
                    .status(200)
                    .message(this.friendRequestService.sendFriendRequest(senderUsername, receiverUsername))
                    .data(null)
                    .build();
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException | FriendRequestAlreadySentException e) {
            System.out.println(e.getMessage());

            ApiResponse response = ApiResponse.builder()
                    .status(404)
                    .message(e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/pending-requests")
    public ResponseEntity<ApiResponse> getPendingRequests(@RequestParam String username) {
        try {
            ApiResponse response = ApiResponse.builder()
                    .status(200)
                    .message("List with all pending requests")
                    .data(this.friendRequestService.getPendingRequests(username))
                    .build();
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());

            ApiResponse response = ApiResponse.builder()
                    .status(404)
                    .message(e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/respond-friend-request/{friendRequestId}")
    public ResponseEntity<ApiResponse> respondToFriendRequest(@PathVariable UUID friendRequestId, @RequestParam Boolean accept) {
        try {
            ApiResponse response = ApiResponse.builder()
                    .status(200)
                    .message(this.friendRequestService.respondToFriendRequest(friendRequestId, accept))
                    .data(null)
                    .build();
            return ResponseEntity.ok(response);
        } catch (FriendRequestNotFoundException e) {
            System.out.println(e.getMessage());

            ApiResponse response = ApiResponse.builder()
                    .status(404)
                    .message(e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
