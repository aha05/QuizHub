package com.quiz.QuizHub.quiz;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuizMapper {
     Quiz toEntity(QuizRequest request);
     void update(QuizRequest request, @MappingTarget Quiz quiz);
}

