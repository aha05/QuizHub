package com.quiz.QuizHub.option;

import lombok.Data;

@Data
public class OptionResponse {
    private Long id;
    private String text;
    private boolean isCorrect;
}
