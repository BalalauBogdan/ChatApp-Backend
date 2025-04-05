package dev.bogdanbalalau.chatapp.controller;

import dev.bogdanbalalau.chatapp.dto.LoginDTO;
import dev.bogdanbalalau.chatapp.dto.RegisterDTO;
import dev.bogdanbalalau.chatapp.entity.User;
import dev.bogdanbalalau.chatapp.exception.IncorrectPasswordException;
import dev.bogdanbalalau.chatapp.exception.UserNotFoundException;
import dev.bogdanbalalau.chatapp.service.UserService;
import dev.bogdanbalalau.chatapp.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterDTO registerDTO) {
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("User registered successfully!")
                .data(this.userService.regiserUser(registerDTO))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginDTO loginDTO) {
        try {
            ApiResponse response = ApiResponse.builder()
                    .status(200)
                    .message("User logged in successfully!")
                    .data(this.userService.loginUser(loginDTO))
                    .build();
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException | IncorrectPasswordException e) {
            System.out.println(e.getMessage());

            ApiResponse response = ApiResponse.builder()
                    .status(404)
                    .message(e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/friends/all/{userId}")
    public ResponseEntity<ApiResponse> findFriendsByUserId(@PathVariable UUID userId) {
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("List with all friends")
                .data(this.userService.findFriendsByUserId(userId))
                .build();
        return ResponseEntity.ok(response);
    }
}
