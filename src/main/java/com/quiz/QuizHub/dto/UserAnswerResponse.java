package com.quiz.QuizHub.dto;

import lombok.Data;

@Data
public class UserAnswerResponse {
    public Long id;
    public Long userId;
    public Long quizId;
    public Long questionId;
    public Long optionId;
    private boolean isCorrect;
}
