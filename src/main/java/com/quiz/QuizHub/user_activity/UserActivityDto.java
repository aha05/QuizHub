package com.quiz.QuizHub.user_activity;

import lombok.Data;

import java.util.List;

@Data
public class UserActivityDto {
    private String name;
    private String level;
    private Long totalQuizzes;
    private Integer completed;
    private List<Badge> badges;
    private Long highestScorePercentage;
    private Integer leaderboard;
    private Integer averageScore;
}
