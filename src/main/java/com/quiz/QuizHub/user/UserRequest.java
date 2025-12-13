package com.quiz.QuizHub.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;
}
