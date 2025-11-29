package com.quiz.QuizHub.controller;

import com.quiz.QuizHub.dto.QuestionRequest;
import com.quiz.QuizHub.dto.QuestionResponse;
import com.quiz.QuizHub.dto.QuizRequest;
import com.quiz.QuizHub.entity.Quiz;
import com.quiz.QuizHub.service.QuizService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("quiz")
@AllArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping
    public ResponseEntity<List<Quiz>> getQuiz() {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.getQuiz());
    }

    @PostMapping
    public ResponseEntity<Quiz> addQuiz(@Valid @RequestBody QuizRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.addQuiz(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@Valid @RequestBody QuizRequest request, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.updateQuiz(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Quiz> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{quizId}/question")
    public ResponseEntity<List<QuestionResponse>> getQuestions(@PathVariable(name = "quizId") Long quizId) {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.getAllQuestions(quizId));
    }

}
