package com.quiz.QuizHub.mapper;

import com.quiz.QuizHub.dto.OptionRequest;
import com.quiz.QuizHub.dto.OptionResponse;
import com.quiz.QuizHub.entity.Option;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OptionMapper {
    Option toEntity(OptionRequest request);
    OptionResponse toDto(Option request);
    List<OptionResponse> toDto(List<Option> request);
    void update(OptionRequest request, @MappingTarget Option option);
}
