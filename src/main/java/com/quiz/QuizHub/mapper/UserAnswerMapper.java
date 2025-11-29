package com.quiz.QuizHub.mapper;

import com.quiz.QuizHub.dto.UserAnswerRequest;
import com.quiz.QuizHub.dto.UserAnswerResponse;
import com.quiz.QuizHub.dto.UserRequest;
import com.quiz.QuizHub.dto.UserResponse;
import com.quiz.QuizHub.entity.User;
import com.quiz.QuizHub.entity.UserAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAnswerMapper {
    UserAnswer toEntity(UserAnswerRequest request);
    void update(UserAnswerRequest request, @MappingTarget UserAnswer userAnswer);

    UserAnswerResponse toDto(UserAnswerRequest userAnswerRequest);
}
