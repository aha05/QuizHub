package com.quiz.QuizHub.dto;

import com.quiz.QuizHub.entity.Quiz;
import com.quiz.QuizHub.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScoreRequest {
    @NotNull(message = "User cannot be null")
    private User user;

    @NotNull(message = "Quiz cannot be null")
    private Quiz quiz;
}
