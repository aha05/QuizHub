package com.quiz.QuizHub.user_activity;

import lombok.Data;

import java.util.List;

@Data
public class QuestionAnswerDTO {
    private Long questionId;
    private List<Long> selectedOptionIds;
}
