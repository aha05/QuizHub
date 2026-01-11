package com.quiz.QuizHub.core.user_activity;

import lombok.Data;

@Data
public class Leaderboard {
    private long userId;
    private String username;
    private Integer score;
    private Integer quizzesAttempted;
}
