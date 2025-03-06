package dev.bogdanbalalau.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDTO {
    private String username;
    private String password;
}
