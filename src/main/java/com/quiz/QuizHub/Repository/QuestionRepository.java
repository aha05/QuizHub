package com.quiz.QuizHub.Repository;

import com.quiz.QuizHub.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
