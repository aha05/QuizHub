package com.quiz.QuizHub.quiz;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuizRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;
}
