package com.quiz.QuizHub.user_activity;

import com.quiz.QuizHub.submission.QuizResult;
import com.quiz.QuizHub.submission.QuizResultRepository;
import com.quiz.QuizHub.user.UserNotFoundException;
import com.quiz.QuizHub.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserStatsService {
    private final UserRepository userRepository;
    private final QuizResultRepository quizResultRepository;

    public UserStatsDto getUserStats(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<QuizResult> results = quizResultRepository.findByUserId(userId);

        UserStatsDto dto = new UserStatsDto();
        dto.setUserId(user.getId());
        dto.setName(user.getName());

        if (results.isEmpty()) {
            dto.setQuizzesAttempted(0L);
            dto.setHighestScorePercentage(0L);
            return dto;
        }

        Map<Long, Integer> bestScoresPerQuiz = new HashMap<>();
        for (QuizResult result : results) {
            bestScoresPerQuiz.merge(
                    result.getQuizId(),
                    result.getScorePercentage(),
                    Math::max
            );
        }

        dto.setQuizzesAttempted((long) bestScoresPerQuiz.size());
        double highestScore = bestScoresPerQuiz.values()
                .stream()
                .mapToDouble(Integer::doubleValue)
                .max()
                .orElse(0L);

        dto.setHighestScorePercentage(Math.round(highestScore));

        return dto;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}

