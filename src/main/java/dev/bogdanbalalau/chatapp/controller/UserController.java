package dev.bogdanbalalau.chatapp.controller;

import dev.bogdanbalalau.chatapp.dto.RegisterDTO;
import dev.bogdanbalalau.chatapp.entity.User;
import dev.bogdanbalalau.chatapp.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public void addUser(@RequestBody RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        this.userService.addUser(user);
    }
}
