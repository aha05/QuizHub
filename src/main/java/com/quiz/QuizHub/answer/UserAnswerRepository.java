package com.quiz.QuizHub.answer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    boolean existsByUser_IdAndQuestion_Id(Long userId, Long questionId);
    List<UserAnswer> findByUserId(Long userId);

    @Query("""
        SELECT COUNT(DISTINCT qa.quiz.id)
        FROM UserAnswer qa
        WHERE qa.user.id = :userId
    """)
    Long countDistinctQuizzes(@Param("userId") Long userId);

    @Query(value = """
        SELECT MAX(score)
        FROM (
            SELECT 
                (SUM(CASE WHEN ua.correct = true THEN 1 ELSE 0 END) * 100.0) 
                / COUNT(ua.id) AS score
            FROM user_answers ua
            WHERE ua.user_id = :userId
            GROUP BY ua.quiz_id
        ) AS scores
    """, nativeQuery = true)
    Double findHighestScorePercentage(@Param("userId") Long userId);
}
