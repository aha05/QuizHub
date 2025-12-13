package com.quiz.QuizHub.user;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
}
