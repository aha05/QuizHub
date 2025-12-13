package com.quiz.QuizHub.option;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quiz.QuizHub.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private boolean isCorrect;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private Question question;
}

