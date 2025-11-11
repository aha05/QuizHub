package com.quiz.QuizHub.mapper;

import com.quiz.QuizHub.dto.QuizRequest;
import com.quiz.QuizHub.entity.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuizMapper {
     Quiz toEntity(QuizRequest request);
     void update(QuizRequest request, @MappingTarget Quiz quiz);
}

