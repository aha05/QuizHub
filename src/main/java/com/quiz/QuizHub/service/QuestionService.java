package com.quiz.QuizHub.service;

import com.quiz.QuizHub.Repository.OptionRepository;
import com.quiz.QuizHub.Repository.QuestionRepository;
import com.quiz.QuizHub.Repository.QuizRepository;
import com.quiz.QuizHub.dto.OptionRequest;
import com.quiz.QuizHub.dto.OptionResponse;
import com.quiz.QuizHub.dto.QuestionRequest;
import com.quiz.QuizHub.dto.QuestionResponse;
import com.quiz.QuizHub.entity.Option;
import com.quiz.QuizHub.entity.Question;
import com.quiz.QuizHub.entity.Quiz;
import com.quiz.QuizHub.mapper.OptionMapper;
import com.quiz.QuizHub.mapper.QuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    public final QuestionMapper questionMapper;
    public final OptionMapper optionMapper;
    public final QuizRepository quizRepository;
    public final QuestionRepository questionRepository;
    public final OptionRepository optionRepository;

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
}
