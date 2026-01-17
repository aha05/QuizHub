package com.quiz.QuizHub.quiz;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuizRequest {
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Difficulty cannot be blank")
    private Difficulty difficulty;

    @NotNull(message = "Status cannot be blank")
    private Status status;

    @NotNull(message = "Category cannot be blank")
    private Long categoryId;

    @Min(1)
    @Max(180)
    @NotNull(message = "TimeLimit cannot be null")
    private Integer timeLimit;

    @NotNull(message = "PassPercentage cannot be null")
    private Integer passPercentage;
}
