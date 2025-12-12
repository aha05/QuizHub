package com.quiz.QuizHub.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
}
