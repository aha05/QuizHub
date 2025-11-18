package com.quiz.QuizHub.dto;

import com.quiz.QuizHub.entity.Question;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class OptionResponse {
    private Long id;
    private String text;
    private boolean isCorrect;
}
