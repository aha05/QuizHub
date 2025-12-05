package com.quiz.QuizHub.dto;

import com.quiz.QuizHub.entity.Quiz;
import com.quiz.QuizHub.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ScoreResponse {
    private Long id;
    private User user;
    private Quiz quiz;
    private Integer score;
}
