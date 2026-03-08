package com.quiz.QuizHub.submission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "quiz_result_answers")
public class QuizResultAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "quiz_result_id", nullable = false)
    private QuizResult quizResult;

    @Column(name = "question_id")
    private Long questionId;

    @ElementCollection
    @CollectionTable(
            name = "quiz_result_selected_options",
            joinColumns = @JoinColumn(name = "quiz_result_answer_id")
    )
    @Column(name = "option_id")
    private Set<Long> selectedOptionIds = new HashSet<>();
}
