package com.quiz.QuizHub.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quiz.QuizHub.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

}

