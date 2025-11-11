package com.quiz.QuizHub.dto;

import com.quiz.QuizHub.entity.Quiz;
import com.quiz.QuizHub.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ScoreRequest {
    private User user;
    private Quiz quiz;
    private String score;
}
