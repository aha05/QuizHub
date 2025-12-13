package com.quiz.QuizHub.answer;

import lombok.Data;

@Data
public class UserAnswerResponse {
    public Long id;
    public Long userId;
    public Long quizId;
    public Long questionId;
    public Long optionId;
    private boolean correct;
}
