package com.quiz.QuizHub.answer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FinishQuizRequest {
    @NotNull(message = "Quiz ID cannot be null")
    private Long quizId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;
}
