package com.quiz.QuizHub.submission;

import com.quiz.QuizHub.auth.AuthService;
import com.quiz.QuizHub.option.Option;
import com.quiz.QuizHub.question.Question;
import com.quiz.QuizHub.quiz.Quiz;
import com.quiz.QuizHub.quiz.QuizRepository;
import com.quiz.QuizHub.score.Score;
import com.quiz.QuizHub.score.ScoreRepository;
import com.quiz.QuizHub.user.User;
import com.quiz.QuizHub.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizResultService {

    private final QuizRepository quizRepository;
    private final QuizResultRepository quizResultRepository;
    private final AuthService authService;
    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;
    private final QuizHistoryMapper quizHistoryMapper;

    public QuizResult submitQuiz(Long quizId, SubmitQuizRequest request) {

        Long userId = authService.getCurrentUser().getId();

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        List<Question> questions = quiz.getQuestions();

        // Validation: all questions must be answered
        if (request.getAnswers().size() != questions.size()) {
            throw new IllegalArgumentException("All questions must be answered");
        }

        int correctCount = 0;

        QuizResult quizResult = new QuizResult();
        quizResult.setUserId(userId);
        quizResult.setQuizId(quizId);
        quizResult.setTotalQuestions(questions.size());
        quizResult.setTimeTaken(request.getTimeTaken());
        quizResult.setSubmittedAt(LocalDateTime.now());

        // Check answers
        for (Question question : questions) {

            QuestionAnswerDTO submittedAnswer = request.getAnswers().stream()
                    .filter(a -> a.getQuestionId().equals(question.getId()))
                    .findFirst()
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Missing answer for question " + question.getId()
                            )
                    );

            // correct option IDs from DB
            Set<Long> correctOptionIds = question.getOptions().stream()
                    .filter(Option::isCorrect)
                    .map(Option::getId)
                    .collect(Collectors.toSet());

            // selected option IDs from frontend
            Set<Long> selectedOptionIds =
                    new HashSet<>(submittedAnswer.getSelectedOptionIds());

            boolean isCorrect = correctOptionIds.equals(selectedOptionIds);

            if (isCorrect) {
                correctCount++;
            }

            // persist answer
            QuizResultAnswer resultAnswer = new QuizResultAnswer();
            resultAnswer.setQuizResult(quizResult);
            resultAnswer.setQuestionId(question.getId());
            resultAnswer.setSelectedOptionIds(selectedOptionIds);

            quizResult.getAnswers().add(resultAnswer);
        }

        // Final Score
        int percentage = (int) Math.round(
                (correctCount * 100.0) / questions.size()
        );

        int passPercentage = quiz.getPassPercentage() != null
                ? quiz.getPassPercentage()
                : 0;

        boolean passed = percentage >= passPercentage;

        quizResult.setCorrectAnswers(correctCount);
        quizResult.setScorePercentage(percentage);
        quizResult.setPassed(passed);

        var bestResult  = getBestResult(userId, quizId);

        if (bestResult != null && bestResult.getScorePercentage() >= quizResult.getScorePercentage()) {
            return bestResult;
        }

        calculateScore(quizId, userId, correctCount);
        return quizResultRepository.save(quizResult);
    }

    public QuizResult getBestResult(Long userId, Long quizId) {
        return quizResultRepository
                .findBestResult(userId, quizId)
                .stream()
                .findFirst()
                .orElse(null);
    }

    public List<QuizResult> getHistory(Long userId) {
        return quizResultRepository.findByUserId(userId);
    }

    public QuizResult getHistoryById(Long id) {
        return quizResultRepository.findById(id).orElse(null);
    }

    public void calculateScore(Long quizId, Long userId, int points){
        var quiz = quizRepository.findById(quizId).orElse(null);
        var user = userRepository.findById(userId).orElse(null);
        Score score = scoreRepository.findScore(user.getId(), quizId);

        if(score == null) {
            createScore(user, quiz, points);
            return;
        }
        score.setScore(score.getScore() + points);
        scoreRepository.save(score);
    }

    private void createScore(User user, Quiz quiz, int points) {
        Score scoreEntity = new Score();
        scoreEntity.setQuiz(quiz);
        scoreEntity.setUser(user);
        scoreEntity.setScore(points);
        scoreRepository.save(scoreEntity);
    }

    public QuizHistoryDto getBestResult(Long quizId){
        Long userId = authService.getCurrentUser().getId();
        var bestResult  = getBestResult(userId, quizId);
        return quizHistoryMapper.toDto(bestResult);
    }

}

