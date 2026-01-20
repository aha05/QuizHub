package com.quiz.QuizHub.core.user_activity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class History {
    private Long id;
    private Long quizId;
    private String quizTitle;
    private int totalQuestions;
    private int correctAnswers;
    private int scorePercentage;
    private String quizCategory;
    private boolean passed;
    private LocalDateTime submittedAt;
}
