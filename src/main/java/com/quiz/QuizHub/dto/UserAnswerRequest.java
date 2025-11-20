package com.quiz.QuizHub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserAnswerRequest {
    @NotNull(message = "User id can not be null")
    public Long userId;

    @NotNull(message = "Question id can not be null")
    public Long questionId;

    @NotNull(message = "Option id can not be null")
    public Long optionId;

    @NotNull(message = "isCorrect cannot be null")
    private boolean isCorrect;
}
