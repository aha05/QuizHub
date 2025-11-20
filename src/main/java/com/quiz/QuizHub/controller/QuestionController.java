package com.quiz.QuizHub.controller;

import com.quiz.QuizHub.dto.*;
import com.quiz.QuizHub.entity.Question;
import com.quiz.QuizHub.entity.User;
import com.quiz.QuizHub.service.QuestionService;
import com.quiz.QuizHub.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("question")
    public ResponseEntity<List<QuestionResponse>> getQuestions() {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestions());
    }

    @PostMapping("quiz/{quizId}/question")
    public ResponseEntity<QuestionResponse> addQuestion(@PathVariable(name = "quizId") Long quizId, @RequestBody QuestionRequest request) {

        return ResponseEntity.status(HttpStatus.OK).body(questionService.addQuestions(quizId, request));
    }

    @PutMapping("question/{id}")
    public ResponseEntity<Question> updateQuestion(@RequestBody QuestionRequest request, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.updateQuestions(request, id));
    }

    @DeleteMapping("question/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("question/{id}/options")
    public ResponseEntity<List<OptionResponse>> getOptions(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getOption(id));
    }

    @PostMapping("question/{id}/options")
    public ResponseEntity<OptionResponse> addOptions(@PathVariable Long id, @RequestBody OptionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.addOption(id, request));
    }

    @PutMapping("question/{id}/option/{optionId}")
    public ResponseEntity<OptionResponse> updateOption(@PathVariable(name = "id") Long id, @PathVariable(name = "optionId") Long optionId, @RequestBody OptionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.updateOption(id, optionId, request));
    }

    @DeleteMapping("question/{id}/option/{optionId}")
    public ResponseEntity<Void> deleteOption(@PathVariable(name = "id") Long id, @PathVariable(name = "optionId") Long optionId) {
        questionService.removeOption(id, optionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("score/{questionId}/option/{optionId}")
    public ResponseEntity<Void> calculateScore(@PathVariable(name = "id") Long id, @PathVariable(name = "optionId") Long optionId) {
        questionService.removeOption(id, optionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
