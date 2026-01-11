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
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name="difficulty")
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(
            mappedBy = "quiz",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="time_limit")
    private Integer timeLimit;

    @Column(name="pass_percentage")
    private Integer passPercentage;

    @Transient
    public int getNumberOfQuestions() {
        return questions != null ? questions.size() : 0;
    }
}

