package com.quiz.QuizHub.answer;

import com.quiz.QuizHub.option.OptionService;
import com.quiz.QuizHub.question.QuestionService;
import com.quiz.QuizHub.quiz.Quiz;
import com.quiz.QuizHub.quiz.QuizRepository;
import com.quiz.QuizHub.score.ScoreRepository;
import com.quiz.QuizHub.score.ScoreResponse;
import com.quiz.QuizHub.score.Score;
import com.quiz.QuizHub.score.ScoreMapper;
import com.quiz.QuizHub.quiz.QuizService;
import com.quiz.QuizHub.user.User;
import com.quiz.QuizHub.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;
    private final UserService userService;
    private final QuestionService questionService;
    private final OptionService optionService;
    private final QuizService quizService;
    private final UserAnswerMapper userAnswerMapper;
    private final QuizRepository quizRepository;
    private final ScoreRepository scoreRepository;
    private final ScoreMapper scoreMapper;

    public UserAnswerResponse userAnswer(Long quizId, UserAnswerRequest request) {
        preventDuplicateAnswer(request); // return error if already answered.

        var user = userService.findUserById(request.getUserId());
        var question = questionService.findQuestionById(request.getQuestionId());
        var currentOption = optionService.findOptionById(request.getOptionId());
        var quiz = quizService.findQuizById(quizId);

        questionService.validateQuestionBelongsToQuiz(quizId, request.getQuestionId()); // throw error if question not exists in quiz
        optionService.validateBelongsToQuestion(request.getOptionId(), request.getQuestionId()); // throw error if option not exists in question

        UserAnswer answer = UserAnswer.create(user, quiz, question, currentOption);

        if(answer.isCorrect()) {
            calculateScore(quizId, user);
        }

        return userAnswerMapper.toDto(userAnswerRepository.save(answer));
    }

    private void preventDuplicateAnswer(UserAnswerRequest request) {
        boolean alreadyAnswered = userAnswerRepository.existsByUser_IdAndQuestion_Id(
                request.getUserId(),
                request.getQuestionId());

        if (alreadyAnswered) {
            throw new DuplicateAnswerException("User has already answered this question");
        }
    }

    public void calculateScore(Long quizId, User user){
        var quiz = quizRepository.findById(quizId).orElse(null);
        Score score = scoreRepository.findScore(user.getId(), quizId);

        if(score == null) {
            createScore(user, quiz);
            return;
        }
        score.setScore(score.getScore() + 1);
        scoreRepository.save(score);
    }

    private void createScore(User user, Quiz quiz) {
        Score scoreEntity = new Score();
        scoreEntity.setQuiz(quiz);
        scoreEntity.setUser(user);
        scoreEntity.setScore(1);
        scoreRepository.save(scoreEntity);
    }


    public List<ScoreResponse> getUserScore(Long userId){
        return scoreRepository.findByUserId(userId)
                .stream()
                .map(scoreMapper::toDto)
                .toList();
    }

    public List<UserAnswerResponse> getUserAnswer(Long userId){
        return userAnswerRepository.findByUserId(userId)
                .stream()
                .map(userAnswerMapper::toDto)
                .toList();
    }

}
