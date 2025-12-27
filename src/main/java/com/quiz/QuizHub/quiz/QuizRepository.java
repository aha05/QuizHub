package com.quiz.QuizHub.quiz;

import com.quiz.QuizHub.question.Question;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("""
      SELECT DISTINCT q
      FROM Question q
      LEFT JOIN FETCH q.options
      WHERE q.quiz.id = :quizId
   """)
    List<Question> findAllWithOptionsByQuizId(Long quizId);
}
