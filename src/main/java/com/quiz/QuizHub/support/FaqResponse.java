package com.quiz.QuizHub.support;

import lombok.Data;

@Data
public class FaqResponse {
    private Long id;
    private String question;
    private String answer;
    private boolean active;
    private Integer sortOrder;
}
