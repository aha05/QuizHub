package com.quiz.QuizHub.dto;

import com.quiz.QuizHub.entity.Quiz;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private Long id;
    private String content;
    private Quiz quiz;
    private List<OptionResponse> options;
}
