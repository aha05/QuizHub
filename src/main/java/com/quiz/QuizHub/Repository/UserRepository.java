package com.quiz.QuizHub.Repository;

import com.quiz.QuizHub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
