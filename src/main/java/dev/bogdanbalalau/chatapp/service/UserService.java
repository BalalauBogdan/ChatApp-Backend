package dev.bogdanbalalau.chatapp.service;

import dev.bogdanbalalau.chatapp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUsername(String username);
    List<User> findAllUsers();
    User addUser(User user);
}
