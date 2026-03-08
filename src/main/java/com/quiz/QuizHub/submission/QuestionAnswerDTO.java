package com.quiz.QuizHub.submission;

import lombok.Data;

import java.util.List;

@Data
public class QuestionAnswerDTO {
    private Long questionId;
    private List<Long> selectedOptionIds;
}
