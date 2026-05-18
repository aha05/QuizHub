package com.quiz.QuizHub.support;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FaqRequest {

    @NotBlank(message = "Question is required")
    private String question;

    @NotBlank(message = "Answer is required")
    private String answer;
    private boolean active;
    private Integer sortOrder;
}
