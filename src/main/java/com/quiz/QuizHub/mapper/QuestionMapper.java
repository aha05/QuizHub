package com.quiz.QuizHub.mapper;

import com.quiz.QuizHub.dto.OptionRequest;
import com.quiz.QuizHub.dto.OptionResponse;
import com.quiz.QuizHub.dto.QuestionRequest;
import com.quiz.QuizHub.entity.Option;
import com.quiz.QuizHub.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question toEntity(QuestionRequest request);
    void update(QuestionRequest request, @MappingTarget Question quiz);

    Option toEntity(OptionRequest request);
    OptionResponse toDto(Option request);
    void update(OptionRequest request, @MappingTarget Option option);
}
