package com.quiz.QuizHub.question;

import com.quiz.QuizHub.option.OptionResponse;
import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private Long id;
    private String content;
    private List<OptionResponse> options;
}
