package com.quiz.QuizHub.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quiz.QuizHub.option.Option;
import com.quiz.QuizHub.quiz.Quiz;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(mappedBy = "question")
    private List<Option> options = new ArrayList<>();
}
