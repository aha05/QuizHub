package com.quiz.QuizHub.quiz;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuizRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Difficulty cannot be blank")
    private Difficulty difficulty;

    @NotBlank(message = "Status cannot be blank")
    private Status status;

    @NotBlank(message = "Category cannot be blank")
    private Category category;
}
