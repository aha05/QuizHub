package com.quiz.QuizHub.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserAnswerResponse {
    public Long userId;
    public Long questionId;
    public Long optionId;
    private boolean isCorrect;
}
