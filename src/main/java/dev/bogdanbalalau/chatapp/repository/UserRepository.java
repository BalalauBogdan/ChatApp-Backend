package dev.bogdanbalalau.chatapp.repository;

import dev.bogdanbalalau.chatapp.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u.friends FROM User u WHERE u.id = :userId")
    Set<User> findFriendsByUserId(@Param("userId") UUID userId);
}
