package com.quiz.QuizHub.user_activity;

import com.quiz.QuizHub.auth.AuthService;
import com.quiz.QuizHub.quiz.Quiz;
import com.quiz.QuizHub.quiz.QuizRepository;
import com.quiz.QuizHub.score.ScoreMapper;
import com.quiz.QuizHub.score.ScoreRepository;
import com.quiz.QuizHub.score.ScoreResponse;
import com.quiz.QuizHub.submission.QuizResult;
import com.quiz.QuizHub.submission.QuizResultRepository;
import com.quiz.QuizHub.user.User;
import com.quiz.QuizHub.user.UserNotFoundException;
import com.quiz.QuizHub.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
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
    private final UserRepository userRepository;


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

    public List<Leaderboard> getLeaderboard() {
        List<QuizResult> results = quizResultRepository.findAll();
        Map<Long, Map<Long, Integer>> userQuizBestScore = new HashMap<>();

        for (QuizResult result : results) {
            userQuizBestScore
                    .computeIfAbsent(result.getUserId(), u -> new HashMap<>())
                    .merge(
                            result.getQuizId(),
                            result.getCorrectAnswers(),
                            Math::max
                    );
        }

        Map<Long, Leaderboard> leaderboardMap = new HashMap<>();

        for (Map.Entry<Long, Map<Long, Integer>> userEntry : userQuizBestScore.entrySet()) {
            Long userId = userEntry.getKey();
            Map<Long, Integer> quizScores = userEntry.getValue();
            Leaderboard leaderboard = new Leaderboard();
            leaderboard.setUserId(userId);
            leaderboard.setScore(
                    quizScores.values().stream().mapToInt(Integer::intValue).sum()
            );
            leaderboard.setQuizzesAttempted(quizScores.size());
            leaderboardMap.put(userId, leaderboard);
        }

        Map<Long, String> userMap = userRepository.findAllById(leaderboardMap.keySet())
                .stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        leaderboardMap.values().forEach(entry ->
                entry.setUsername(userMap.get(entry.getUserId()))
        );

        List<Leaderboard> leaderboard = new ArrayList<>(leaderboardMap.values());
        leaderboard.sort(Comparator.comparingInt(Leaderboard::getScore).reversed());

        int rank = 1;
        int prevScore = -1;
        int displayRank = 0;

        for (Leaderboard entry : leaderboard) {
            displayRank++;
            if (entry.getScore() != prevScore) {
                rank = displayRank;
                prevScore = entry.getScore();
            }
            entry.setRank(rank);
        }

        return leaderboard;
    }

    public UserActivityDto getUserActivity() {
        Long userId = authService.getCurrentUser().getId();
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<QuizResult> results = quizResultRepository.findByUserId(userId);

        UserActivityDto dto = new UserActivityDto();
        dto.setName(user.getName());
        dto.setTotalQuizzes(quizRepository.count());

        if (results.isEmpty()) {
            dto.setCompleted(0);
            dto.setHighestScorePercentage(0L);
            dto.setAverageScore(0);
            dto.setLevel("Beginner");
            dto.setBadges(Collections.emptyList());
            dto.setLeaderboard(null);
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

        dto.setCompleted((int) bestScoresPerQuiz.size());
        double highestScore = bestScoresPerQuiz.values()
                .stream()
                .mapToDouble(Integer::doubleValue)
                .max()
                .orElse(0L);
        
        dto.setHighestScorePercentage(Math.round(highestScore));
        double averageScore = bestScoresPerQuiz.values()
                .stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0L);
        dto.setAverageScore((int) Math.round(averageScore));
        dto.setLevel(determineLevel(averageScore));
        dto.setBadges(determineBadges(dto.getCompleted(), highestScore));
        dto.setLeaderboard(findUserRank(userId));

        return dto;
    }

    private String determineLevel(double avgScore) {
        if (avgScore >= 90) return "Expert";
        if (avgScore >= 75) return "Advanced";
        if (avgScore >= 50) return "Intermediate";
        return "Beginner";
    }

    private List<Badge> determineBadges(Integer completed, double highestScore) {
        List<Badge> badges = new ArrayList<>();

        if (completed >= 1) badges.add(Badge.FIRST_QUIZ);
        if (completed >= 5) badges.add(Badge.CONSISTENT);
        if (highestScore >= 90) badges.add(Badge.HIGH_SCORER);

        return badges;
    }

    private Integer findUserRank(Long userId) {

        List<Leaderboard> leaderboard = getLeaderboard(); // your existing method

        return leaderboard.stream()
                .filter(entry -> entry.getUserId().equals(userId))
                .map(Leaderboard::getRank)
                .findFirst()
                .orElse(null);
    }

}
