package com.quiz.QuizHub.answer;

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
}
