package com.quiz.QuizHub.submission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    @Query("""
        SELECT qr FROM QuizResult qr
        WHERE qr.userId = :userId
          AND qr.quizId = :quizId
        ORDER BY qr.scorePercentage DESC,
                 qr.timeTaken ASC,
                 qr.submittedAt ASC
      """)
    List<QuizResult> findBestResult(
            @Param("userId") Long userId,
            @Param("quizId") Long quizId
    );

    List<QuizResult> findByUserId(Long userId);

}
