package com.quiz.QuizHub.service;

import com.quiz.QuizHub.Repository.*;
import com.quiz.QuizHub.dto.*;
import com.quiz.QuizHub.entity.*;
import com.quiz.QuizHub.mapper.OptionMapper;
import com.quiz.QuizHub.mapper.QuestionMapper;
import com.quiz.QuizHub.mapper.UserAnswerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    public final QuestionMapper questionMapper;
    public final OptionMapper optionMapper;
    public final UserRepository userRepository;
    public final QuizRepository quizRepository;
    public final QuestionRepository questionRepository;
    public final OptionRepository optionRepository;
    public final UserAnswerRepository userAnswerRepository;
    public final UserAnswerMapper userAnswerMapper;
    public final ScoreRepository scoreRepository;



    public List<QuestionResponse> getQuestions(){
        return questionRepository.findAll()
                .stream()
                .map(questionMapper::toDto)
                .toList();
    }

    public QuestionResponse addQuestions(Long quizId, QuestionRequest request){
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + quizId));

        Question question = questionMapper.toEntity(request);
        question.setQuiz(quiz);

        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toDto(savedQuestion);
    }

    public Question updateQuestions(QuestionRequest request, Long id){
        var question = questionRepository.findById(id).orElse(null);
        if(question == null) return null;
        questionMapper.update(request, question);
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
        return;
    }

    public List<OptionResponse> getOption(Long id) {
        var question = questionRepository.findById(id) .orElseThrow(() -> new RuntimeException("Option not found with id: " + id));
        var options = question.getOptions();
        return optionMapper.toDto(options);
    }

    public OptionResponse addOption(Long id, OptionRequest request) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));

        Option option = optionMapper.toEntity(request);
        option.setQuestion(question);

        Option savedOption = optionRepository.save(option);
        return optionMapper.toDto(savedOption);
    }

    public OptionResponse updateOption(Long id, Long optionId, OptionRequest request) {
        var question = questionRepository.findById(id).orElse(null);
        var option = optionRepository.findById(optionId).orElse(null);
        if(question == null || option == null) return null;
        optionMapper.update(request, option);
        return optionMapper.toDto(option);
    }

    public void removeOption(Long id, Long optionId) {
        optionRepository.deleteById(optionId);
    }

    public UserAnswerResponse userAnswer(Long quizId, UserAnswerRequest request) {
        var count = userAnswerRepository.checkIfAnswerAlreadyExist(request.getUserId(), request.getQuestionId());
        System.out.println("count:" + count);
        if(count > 0) return null;

        var user = userRepository.findById(request.userId).orElse(null);
        var question = questionRepository.findById(request.questionId).orElse(null);
        var option = optionRepository.findById(request.optionId).orElse(null);

        UserAnswer answer = new UserAnswer();
        answer.setUser(user);
        answer.setQuestion(question);
        answer.setOption(option);
        userAnswerRepository.save(answer);

        var result = userAnswerMapper.toDto(request);
        if(option == null) return null;
        result.setCorrect(option.isCorrect());

        if(option.isCorrect()) {
            calculateScore(quizId, user);
        }

        return result;
    }

    public void calculateScore(Long quizId, User user){
        var quiz = quizRepository.findById(quizId).orElse(null);
        Score scoreEntity = new Score();
        scoreEntity.setQuiz(quiz);
        scoreEntity.setUser(user);
        Score score = scoreRepository.findScore(user.getId(), quizId);

        if(score == null) {
            scoreEntity.setScore(1);
            scoreRepository.save(scoreEntity);
            return;
        }

        score.setScore(score.getScore() + 1);
        scoreRepository.save(score);
    }

}
