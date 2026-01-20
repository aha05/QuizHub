package com.quiz.QuizHub.core.user_activity;

import com.quiz.QuizHub.quiz.Quiz;
import com.quiz.QuizHub.submission.QuizResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface HistoryMapper {
    @Mapping(target = "id", source = "quizResult.id")
    @Mapping(target = "quizTitle", source = "quiz.title")
    @Mapping(target = "quizCategory", source = "quiz.category.name")
    History toHistory(QuizResult quizResult, Quiz quiz);

    default List<History> toHistory(
            List<QuizResult> results,
            Map<Long, Quiz> quizMap
    ) {
        return results.stream()
                .map(r -> toHistory(r, quizMap.get(r.getQuizId())))
                .toList();
    }
}
