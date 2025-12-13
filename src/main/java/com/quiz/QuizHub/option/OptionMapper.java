package com.quiz.QuizHub.option;

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
