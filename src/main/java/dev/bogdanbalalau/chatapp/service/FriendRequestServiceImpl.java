package dev.bogdanbalalau.chatapp.service;

import dev.bogdanbalalau.chatapp.dto.FriendRequestDTO;
import dev.bogdanbalalau.chatapp.entity.FriendRequest;
import dev.bogdanbalalau.chatapp.entity.RequestStatus;
import dev.bogdanbalalau.chatapp.entity.User;
import dev.bogdanbalalau.chatapp.exception.FriendRequestAlreadySentException;
import dev.bogdanbalalau.chatapp.exception.FriendRequestNotFoundException;
import dev.bogdanbalalau.chatapp.exception.UserNotFoundException;
import dev.bogdanbalalau.chatapp.repository.FriendRequestRepository;
import dev.bogdanbalalau.chatapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {
    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;

    public FriendRequestServiceImpl(UserRepository userRepository, FriendRequestRepository friendRequestRepository) {
        this.userRepository = userRepository;
        this.friendRequestRepository = friendRequestRepository;
    }

    @Override
    public String sendFriendRequest(String senderUsername, String receiverUsername) throws UserNotFoundException, FriendRequestAlreadySentException {
        User sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        User receiver = userRepository.findByUsername(receiverUsername)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        if (friendRequestRepository.findBySenderAndReceiver(sender, receiver).isPresent()) {
            throw new FriendRequestAlreadySentException("Friend request already sent!");
        }

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        friendRequest.setStatus(RequestStatus.PENDING);
        friendRequestRepository.save(friendRequest);

        return "Friend request sent!";
    }

    @Override
    public List<FriendRequestDTO> getPendingRequests(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        return friendRequestRepository.findByReceiverAndStatus(user, RequestStatus.PENDING)
                .stream()
                .map(request -> new FriendRequestDTO(request.getId(), request.getSender().getUsername()))
                .collect(Collectors.toList());
    }

    @Override
    public String respondToFriendRequest(UUID friendRequestId, Boolean accept) throws FriendRequestNotFoundException {
        FriendRequest request = this.friendRequestRepository.findById(friendRequestId)
                .orElseThrow(() -> new FriendRequestNotFoundException("Friend request not found!"));

        if (accept) {
            request.setStatus(RequestStatus.ACCEPTED);
            request.getSender().getFriends().add(request.getReceiver());
            request.getReceiver().getFriends().add(request.getSender());
        } else {
            request.setStatus(RequestStatus.REJECTED);
        }

        friendRequestRepository.save(request);
        return accept ? "Friend request accepted" : "Friend request rejected";
    }
}
