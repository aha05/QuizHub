package com.quiz.QuizHub.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private Long id;
    private String content;
    private List<OptionResponse> options;
}
