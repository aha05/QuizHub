package com.quiz.QuizHub.Repository;

import com.quiz.QuizHub.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
