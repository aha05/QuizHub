package com.quiz.QuizHub.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    @Query("SELECT s FROM Score s WHERE s.user.id = :userId AND s.quiz.id = :quizId")
    Score findScore(@Param("userId") Long userId, @Param("quizId") Long quizId);
    List<Score> findByUserId(Long userId);

    @Query("SELECT COALESCE(SUM(s.score), 0) FROM Score s WHERE s.user.id = :userId")
    int getTotalScoreByUser(@Param("userId") Long userId);
}
