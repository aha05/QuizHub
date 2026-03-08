package com.quiz.QuizHub.submission;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SubmitQuizRequest {
    @NotNull(message = "timeTaken is required.")
    private int timeTaken; // seconds

    @NotNull(message = "answers is required.")
    private List<QuestionAnswerDTO> answers;
}
