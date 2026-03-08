package com.quiz.QuizHub.submission;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "quiz_result")
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "quiz_id")
    private Long quizId;

    @Column(name = "total_questions")
    private int totalQuestions;

    @Column(name = "correct_answers")
    private int correctAnswers;

    @Column(name = "score_percentage")
    private int scorePercentage;

    @Column(name = "time_taken")
    private int timeTaken; // in seconds

    @Column(name = "passed")
    private boolean passed;

    private LocalDateTime submittedAt;

    @Column(name = "answers")
    @OneToMany(
            mappedBy = "quizResult",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<QuizResultAnswer> answers = new ArrayList<>();
}
