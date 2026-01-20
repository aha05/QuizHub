package com.quiz.QuizHub.submission;

import org.apache.catalina.LifecycleState;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizHistoryMapper {
    QuizHistoryDto toDto(QuizResult quizResult);
}
