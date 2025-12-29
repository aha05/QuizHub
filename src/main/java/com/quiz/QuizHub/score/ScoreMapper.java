package com.quiz.QuizHub.score;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScoreMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "quizId", source = "quiz.id")
   ScoreResponse toDto(Score scores);
}
