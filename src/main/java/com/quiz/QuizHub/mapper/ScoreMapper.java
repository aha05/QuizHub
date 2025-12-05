package com.quiz.QuizHub.mapper;

import com.quiz.QuizHub.dto.ScoreResponse;
import com.quiz.QuizHub.dto.UserRequest;
import com.quiz.QuizHub.dto.UserResponse;
import com.quiz.QuizHub.entity.Score;
import com.quiz.QuizHub.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScoreMapper {
   ScoreResponse toDto(Score scores);
}
