package com.quiz.QuizHub.dto;

import com.quiz.QuizHub.entity.Question;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class QuizRequest {
    private String title;
    private String description;
}
