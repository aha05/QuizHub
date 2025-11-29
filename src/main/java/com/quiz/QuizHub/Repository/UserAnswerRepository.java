package com.quiz.QuizHub.Repository;


import com.quiz.QuizHub.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    @Query("SELECT Count(u) FROM UserAnswer u WHERE u.user.id = :userId AND u.question.id = :questionId AND u.is_correct = true")
    Integer checkIfAnswerAlreadyExist(@Param("userId") Long userId, @Param("questionId") Long questionId);
}
