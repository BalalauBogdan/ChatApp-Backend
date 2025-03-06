package dev.bogdanbalalau.chatapp.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private Integer status;
    private String message;
    private Object data;
}
