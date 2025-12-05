package com.quiz.QuizHub.mapper;

import com.quiz.QuizHub.dto.UserAnswerRequest;
import com.quiz.QuizHub.dto.UserAnswerResponse;
import com.quiz.QuizHub.entity.UserAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserAnswerMapper {
    UserAnswer toEntity(UserAnswerRequest request);
    void update(UserAnswerRequest request, @MappingTarget UserAnswer userAnswer);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "quizId", source = "quiz.id")
    @Mapping(target = "questionId", source = "question.id")
    @Mapping(target = "optionId", source = "option.id")
    UserAnswerResponse toDto(UserAnswer userAnswer);
}
