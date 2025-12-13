package com.quiz.QuizHub.question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    boolean existsByIdAndQuiz_Id(Long questionId, Long quizId);
}
