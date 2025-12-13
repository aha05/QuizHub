package com.quiz.QuizHub.question;

import com.quiz.QuizHub.quiz.Quiz;
import com.quiz.QuizHub.quiz.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionRepository questionRepository;
    private final QuizService quizService;

    public List<QuestionResponse> getQuestions(){
        return questionRepository.findAll()
                .stream()
                .map(questionMapper::toDto)
                .toList();
    }

    public QuestionResponse addQuestion(Long quizId, QuestionRequest request){
        Quiz quiz = quizService.findQuizById(quizId);
        Question question = questionMapper.toEntity(request);
        question.setQuiz(quiz);
        return questionMapper.toDto(questionRepository.save(question));
    }

    public QuestionResponse updateQuestion(QuestionRequest request, Long id){
        var question = findQuestionById(id);
        questionMapper.update(request, question);
        return questionMapper.toDto(questionRepository.save(question));
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Question findQuestionById(Long questionId){
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + questionId));
    }

    public void validateQuestionBelongsToQuiz(Long quizId, Long questionId) {
        if (!questionRepository.existsByIdAndQuiz_Id(questionId, quizId)) {
            throw new QuestionNotInQuizException(
                    "Question does not belong to the specified quiz"
            );
        }
    }
}
