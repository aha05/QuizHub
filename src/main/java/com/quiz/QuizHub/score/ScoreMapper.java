package com.quiz.QuizHub.score;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScoreMapper {
   ScoreResponse toDto(Score scores);
}
