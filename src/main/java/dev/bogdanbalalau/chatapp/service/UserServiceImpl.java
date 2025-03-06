package dev.bogdanbalalau.chatapp.service;

import dev.bogdanbalalau.chatapp.entity.User;
import dev.bogdanbalalau.chatapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public User addUser(User user) {
        return this.userRepository.save(user);
    }
}
