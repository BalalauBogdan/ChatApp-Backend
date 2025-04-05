package dev.bogdanbalalau.chatapp.repository;

import dev.bogdanbalalau.chatapp.entity.FriendRequest;
import dev.bogdanbalalau.chatapp.entity.RequestStatus;
import dev.bogdanbalalau.chatapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, UUID> {
    Optional<FriendRequest> findLastBySenderAndReceiver(User sender, User receiver);

    List<FriendRequest> findByReceiverAndStatus(User receiver, RequestStatus status);
}
