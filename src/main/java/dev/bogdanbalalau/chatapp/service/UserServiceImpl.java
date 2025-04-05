package dev.bogdanbalalau.chatapp.service;

import dev.bogdanbalalau.chatapp.dto.FriendDTO;
import dev.bogdanbalalau.chatapp.dto.LoginDTO;
import dev.bogdanbalalau.chatapp.dto.RegisterDTO;
import dev.bogdanbalalau.chatapp.dto.UserDTO;
import dev.bogdanbalalau.chatapp.entity.User;
import dev.bogdanbalalau.chatapp.exception.IncorrectPasswordException;
import dev.bogdanbalalau.chatapp.exception.UserNotFoundException;
import dev.bogdanbalalau.chatapp.repository.UserRepository;
import dev.bogdanbalalau.chatapp.utils.PasswordEncryption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User regiserUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(PasswordEncryption.encryptPassword(registerDTO.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws UserNotFoundException, IncorrectPasswordException {
        User user = this.userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        if (PasswordEncryption.checkPassword(loginDTO.getPassword(), user.getPassword())) {
            return new UserDTO(user.getId(), user.getUsername(), user.getPassword());
        } else {
            throw new IncorrectPasswordException("Password is incorrect!");
        }
    }

    @Override
    public List<FriendDTO> findFriendsByUserId(UUID userId) {
        return this.userRepository.findFriendsByUserId(userId)
                .stream()
                .map(friend -> new FriendDTO(friend.getId(), friend.getUsername()))
                .collect(Collectors.toList());
    }
}
