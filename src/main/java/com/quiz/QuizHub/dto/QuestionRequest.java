package com.quiz.QuizHub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuestionRequest {
    @NotBlank(message = "Question content cannot be blank")
    private String content;
}
