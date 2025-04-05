package dev.bogdanbalalau.chatapp.service;

import dev.bogdanbalalau.chatapp.dto.FriendDTO;
import dev.bogdanbalalau.chatapp.dto.LoginDTO;
import dev.bogdanbalalau.chatapp.dto.RegisterDTO;
import dev.bogdanbalalau.chatapp.dto.UserDTO;
import dev.bogdanbalalau.chatapp.entity.User;
import dev.bogdanbalalau.chatapp.exception.IncorrectPasswordException;
import dev.bogdanbalalau.chatapp.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> findUserByUsername(String username);
    List<User> findAllUsers();
    User regiserUser(RegisterDTO registerDTO);
    UserDTO loginUser(LoginDTO loginDTO) throws UserNotFoundException, IncorrectPasswordException;
    List<FriendDTO> findFriendsByUserId(UUID userId);
}
