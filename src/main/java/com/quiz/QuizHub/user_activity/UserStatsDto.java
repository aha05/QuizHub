package com.quiz.QuizHub.user_activity;

import lombok.Data;

@Data
public class UserStatsDto {
    private Long userId;
    private String Name;
    private Long quizzesAttempted;
    private Long highestScorePercentage;
}
