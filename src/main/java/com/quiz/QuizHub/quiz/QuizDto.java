package com.quiz.QuizHub.quiz;

import lombok.Data;

@Data
public class QuizDto {
    private Long id;
    private String title;
    private String description;
    private Category category;
    private Difficulty difficulty;
    private Status status;
    private Integer questions;
}
