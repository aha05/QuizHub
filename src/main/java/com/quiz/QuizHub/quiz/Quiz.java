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
    @Column(name="name")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

}

