package com.quiz.QuizHub.score;

import com.quiz.QuizHub.quiz.Quiz;
import com.quiz.QuizHub.user.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScoreRequest {
    @NotNull(message = "User cannot be null")
    private User user;

    @NotNull(message = "Quiz cannot be null")
    private Quiz quiz;
}
