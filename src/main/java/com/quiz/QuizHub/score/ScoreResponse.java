package com.quiz.QuizHub.score;

import com.quiz.QuizHub.quiz.Quiz;
import com.quiz.QuizHub.user.User;
import lombok.Data;

@Data
public class ScoreResponse {
    private Long id;
    private User user;
    private Quiz quiz;
    private Integer score;
}
