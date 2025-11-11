package com.quiz.QuizHub.dto;

import com.quiz.QuizHub.entity.Option;
import com.quiz.QuizHub.entity.Quiz;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class QuestionRequest {
    private String content;
}
