package com.quiz.QuizHub.answer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStatsDto {
    private Long userId;
    private String Name;
    private Long quizzesAttempted;
    private Double highestScorePercentage;
}
