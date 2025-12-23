package com.quiz.QuizHub.quiz;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuizMapper {
     Quiz toEntity(QuizRequest request);
     void update(QuizRequest request, @MappingTarget Quiz quiz);

     @Mapping(target = "questions", expression = "java(quiz.getNumberOfQuestions())")
     QuizDto toDto(Quiz quiz);

    Category toEntity(CategoryRequest request);
}

