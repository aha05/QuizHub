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
    @Column(name="id")
    private Long id;

    @Column(name="text")
    private String text;

    @Column(name="is_correct")
    private boolean isCorrect;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}

