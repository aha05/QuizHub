package com.quiz.QuizHub.quiz;

import com.quiz.QuizHub.question.QuestionResponse;
import com.quiz.QuizHub.question.QuestionNotFoundException;
import com.quiz.QuizHub.question.QuestionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final QuestionMapper questionMapper;
    private final CategoryRepository categoryRepository;

    public List<QuizDto> getQuizzes(){
        return quizRepository.findAll()
                .stream()
                .map(quizMapper::toDto)
                .toList();
    }

    public QuizDto addQuiz(QuizRequest request){
        var quiz = quizMapper.toEntity(request);
        return quizMapper.toDto(quizRepository.save(quiz));
    }

    public QuizDto updateQuiz(QuizRequest request, Long quizId){
        var quiz = findQuizById(quizId);
        quizMapper.update(request, quiz);
        return quizMapper.toDto(quizRepository.save(quiz));
    }

    public void deleteQuiz(Long id) {
       quizRepository.deleteById(id);
    }

    public List<QuestionResponse> getAllQuestions(Long quizId) {
       var quiz = findQuizById(quizId);
       var questions = quiz.getQuestions();
       if (questions == null) throw new QuestionNotFoundException("No question found.");
       return questionMapper.toDto(quiz.getQuestions());
    }

    public Quiz findQuizById(Long quizId){
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id: " + quizId));
    }

    public Category addCategory(CategoryRequest request){
        var category = quizMapper.toEntity(request);
        return categoryRepository.save(category);
    }

}
