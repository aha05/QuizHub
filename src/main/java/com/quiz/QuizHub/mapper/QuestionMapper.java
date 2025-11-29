package com.quiz.QuizHub.mapper;

import com.quiz.QuizHub.dto.QuestionRequest;
import com.quiz.QuizHub.dto.QuestionResponse;
import com.quiz.QuizHub.entity.Question;
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
