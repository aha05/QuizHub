package com.quiz.QuizHub.mapper;

import com.quiz.QuizHub.dto.OptionRequest;
import com.quiz.QuizHub.dto.OptionResponse;
import com.quiz.QuizHub.dto.QuestionRequest;
import com.quiz.QuizHub.dto.QuestionResponse;
import com.quiz.QuizHub.entity.Option;
import com.quiz.QuizHub.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question toEntity(QuestionRequest request);
    QuestionResponse toDto(Question request);
    void update(QuestionRequest request, @MappingTarget Question quiz);
}
