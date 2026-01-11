package com.quiz.QuizHub.core.user_activity;

import lombok.Data;

@Data
public class UserActivityDto {
    private String name;
    private String level;
    private Long totalQuizzes;
    private Double highestScorePercentage;
    private Integer leaderboard;
    private Double averageScore;
}
