package com.quiz.QuizHub.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String password;
}
