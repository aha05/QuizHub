package com.quiz.QuizHub.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    @NotNull(message = "Password cannot be null")
    private String oldPassword;

    @NotNull(message = "Password cannot be null")
    private String newPassword;
}
