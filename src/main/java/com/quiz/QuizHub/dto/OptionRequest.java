package com.quiz.QuizHub.dto;

import com.quiz.QuizHub.entity.Question;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OptionRequest {
    @NotBlank(message = "Option text cannot be blank")
    private String text;

    @NotNull(message = "isCorrect cannot be null")
    private boolean isCorrect;
}
