package com.quiz.QuizHub.service;

import com.quiz.QuizHub.Repository.QuizRepository;
import com.quiz.QuizHub.dto.QuestionResponse;
import com.quiz.QuizHub.dto.QuizRequest;
import com.quiz.QuizHub.entity.Quiz;
import com.quiz.QuizHub.mapper.QuestionMapper;
import com.quiz.QuizHub.mapper.QuizMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final QuestionMapper questionMapper;

    public List<Quiz> getQuiz(){
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Long quizId){
        var quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz == null) {
            return null;
        }
//        quiz.getQuestions();

        return quiz;
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

    public List<QuestionResponse> getAllQuestions(Long quizId) {
       var quiz = quizRepository.findById(quizId).orElse(null);
       if(quiz == null) return null;
       var questions = quiz.getQuestions();
       if (questions == null) return null;
       return questionMapper.toDto(quiz.getQuestions());
    }
}
