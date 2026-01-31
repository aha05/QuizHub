package com.quiz.QuizHub.user_activity;

import lombok.Data;

@Data
public class Leaderboard {
    private Long userId;
    private String username;
    private Integer score;
    private Integer quizzesAttempted;
    private int rank;
}
