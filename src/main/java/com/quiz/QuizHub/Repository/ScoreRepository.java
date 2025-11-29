package com.quiz.QuizHub.Repository;

import com.quiz.QuizHub.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    @Query("SELECT s FROM Score s WHERE s.user.id = :userId AND s.quiz.id = :quizId")
    Score findScore(@Param("userId") Long userId, @Param("quizId") Long quizId);
}
