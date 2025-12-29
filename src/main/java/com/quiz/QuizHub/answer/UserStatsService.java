package com.quiz.QuizHub.answer;

import com.quiz.QuizHub.score.ScoreRepository;
import com.quiz.QuizHub.user.UserNotFoundException;
import com.quiz.QuizHub.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserStatsService {

    private final UserRepository userRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final ScoreRepository scoreRepository;

    public UserStatsDto getUserStats(Long userId) {

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        long quizzesAttempted =
                userAnswerRepository.countDistinctQuizzes(userId);

        double highestScore =
                Optional.ofNullable(
                        userAnswerRepository.findHighestScorePercentage(userId)
                ).orElse(0.0);

        System.out.println(highestScore);

        return UserStatsDto.builder()
                .userId(user.getId())
                .Name(user.getName())
                .quizzesAttempted(quizzesAttempted)
                .highestScorePercentage(round(highestScore))
                .build();
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}

