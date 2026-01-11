package com.quiz.QuizHub.user;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleUpdateRequest {
    @NotNull(message = "Role is required.")
    @Enumerated(EnumType.STRING)
    private Role role;
}
