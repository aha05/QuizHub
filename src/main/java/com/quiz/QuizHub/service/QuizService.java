package com.quiz.QuizHub.service;

import com.quiz.QuizHub.Repository.QuizRepository;
import com.quiz.QuizHub.dto.QuizRequest;
import com.quiz.QuizHub.entity.Quiz;
import com.quiz.QuizHub.mapper.QuizMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    public List<Quiz> getQuiz(){
        return quizRepository.findAll();
    }

    public Quiz addQuiz(QuizRequest request){
        var quiz = quizMapper.toEntity(request);
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(QuizRequest request, Long id){
        var quiz = quizRepository.findById(id).orElse(null);
        if(quiz == null) return null;
        quizMapper.update(request, quiz);
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
       quizRepository.deleteById(id);
       return;
    }
}
