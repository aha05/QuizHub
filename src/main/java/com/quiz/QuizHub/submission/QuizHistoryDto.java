package com.quiz.QuizHub.submission;

import lombok.Data;

import java.util.List;

@Data
public class QuizHistoryDto {
    private int timeTaken;
    private List<QuestionAnswerDTO> answers;
}
