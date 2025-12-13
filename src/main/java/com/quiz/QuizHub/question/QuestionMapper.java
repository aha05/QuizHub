package com.quiz.QuizHub.question;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question toEntity(QuestionRequest request);
    QuestionResponse toDto(Question request);
    List<QuestionResponse> toDto(List<Question> request);
    void update(QuestionRequest request, @MappingTarget Question quiz);
}
