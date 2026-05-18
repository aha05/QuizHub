package com.quiz.QuizHub.support;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FaqMapper {
    void update(FaqRequest request, @MappingTarget Faq faq);
    Faq toEntity(FaqRequest request);
    FaqResponse toDto(Faq faq);
}
