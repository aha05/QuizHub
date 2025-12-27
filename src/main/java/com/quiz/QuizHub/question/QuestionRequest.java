package com.quiz.QuizHub.question;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestionRequest {
    @NotBlank(message = "Question content cannot be blank")
    private String content;

    @NotNull(message = "Question Type cannot be blank")
    private Type type;
}
