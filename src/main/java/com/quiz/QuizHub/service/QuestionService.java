package com.quiz.QuizHub.service;

import com.quiz.QuizHub.Repository.OptionRepository;
import com.quiz.QuizHub.Repository.QuestionRepository;
import com.quiz.QuizHub.dto.OptionRequest;
import com.quiz.QuizHub.dto.OptionResponse;
import com.quiz.QuizHub.dto.QuestionRequest;
import com.quiz.QuizHub.entity.Option;
import com.quiz.QuizHub.entity.Question;
import com.quiz.QuizHub.mapper.QuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    public final QuestionMapper questionMapper;
    public final QuestionRepository questionRepository;
    public final OptionRepository optionRepository;

    public List<Question> getQuestions(){
        return questionRepository.findAll();
    }

    public Question addQuestions(QuestionRequest request){
        var question = questionMapper.toEntity(request);
        return questionRepository.save(question);
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

    public OptionResponse addOption(Long id, OptionRequest request) {
        var question = questionRepository.findById(id).orElse(null);
        if(question == null) return null;
        var option = questionMapper.toEntity(request);
        question.setOptions(List.of(option));
        option.setQuestion(question);
        questionRepository.save(question);
        optionRepository.save(option);

        return questionMapper.toDto(option);
    }

    public OptionResponse updateOption(Long id, Long optionId, OptionRequest request) {
        var question = questionRepository.findById(id).orElse(null);
        var option = optionRepository.findById(optionId).orElse(null);
        if(question == null || option == null) return null;
        questionMapper.update(request, option);
        return questionMapper.toDto(option);
    }

    public void removeOption(Long id, Long optionId) {
        var question = questionRepository.findById(id).orElse(null);
        var option = optionRepository.findById(optionId).orElse(null);
    }
}
