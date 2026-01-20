package com.quiz.QuizHub.core.user_activity;

import com.quiz.QuizHub.auth.AuthService;
import com.quiz.QuizHub.auth.Jwt;
import com.quiz.QuizHub.quiz.Quiz;
import com.quiz.QuizHub.quiz.QuizRepository;
import com.quiz.QuizHub.score.Score;
import com.quiz.QuizHub.score.ScoreMapper;
import com.quiz.QuizHub.score.ScoreRepository;
import com.quiz.QuizHub.score.ScoreResponse;
import com.quiz.QuizHub.submission.QuizResult;
import com.quiz.QuizHub.submission.QuizResultRepository;
import com.quiz.QuizHub.submission.QuizResultService;
import com.quiz.QuizHub.user.User;
import com.quiz.QuizHub.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserActivityService {
    private final QuizResultRepository quizResultRepository;
    private final HistoryMapper historyMapper;
    private final QuizRepository quizRepository;
    private final AuthService authService;
    private final ScoreRepository scoreRepository;
    private final ScoreMapper scoreMapper;


    public List<History> getHistory() {
        Long userId = authService.getCurrentUser().getId();
        var quizResults = quizResultRepository.findByUserId(userId);
        List<Long> quizIds = quizResults.stream()
                .map(QuizResult::getQuizId)
                .distinct()
                .toList();

        List<Quiz> quizzes = quizRepository.findAllById(quizIds);
        Map<Long, Quiz> quizMap = quizzes.stream()
                .collect(Collectors.toMap(Quiz::getId, q -> q));

        return historyMapper.toHistory(quizResults, quizMap);
    }



    public List<ScoreResponse> getUserScore(Long userId){
        return scoreRepository.findByUserId(userId)
                .stream()
                .map(scoreMapper::toDto)
                .toList();
    }
}
