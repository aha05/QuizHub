package com.quiz.QuizHub.dto;

import com.quiz.QuizHub.entity.Question;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class OptionRequest {
    private String text;
    private boolean isCorrect;
    private Question question;
}
