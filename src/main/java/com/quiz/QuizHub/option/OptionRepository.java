package com.quiz.QuizHub.option;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
    boolean existsByIdAndQuestion_Id(Long optionId, Long questionId);
}
