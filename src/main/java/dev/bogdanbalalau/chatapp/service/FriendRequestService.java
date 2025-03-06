package dev.bogdanbalalau.chatapp.service;

import dev.bogdanbalalau.chatapp.dto.FriendRequestDTO;
import dev.bogdanbalalau.chatapp.exception.FriendRequestAlreadySentException;
import dev.bogdanbalalau.chatapp.exception.FriendRequestNotFoundException;
import dev.bogdanbalalau.chatapp.exception.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface FriendRequestService {
    String sendFriendRequest(String senderUsername, String receiverUsername) throws UserNotFoundException, FriendRequestAlreadySentException;
    List<FriendRequestDTO> getPendingRequests(String username) throws UserNotFoundException;
    String respondToFriendRequest(UUID friendRequestId, Boolean accept) throws FriendRequestNotFoundException;
}
