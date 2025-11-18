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
@RequestMapping("question")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions() {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestions());
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.addQuestions(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@RequestBody QuestionRequest request, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.updateQuestions(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/options")
    public ResponseEntity<OptionResponse> addOptions(@PathVariable Long id, @RequestBody OptionRequest request) {

        return ResponseEntity.status(HttpStatus.OK).body(questionService.addOption(id, request));
    }

    @PutMapping("/{id}/option/{optionId}")
    public ResponseEntity<Void> updateOption(@PathVariable Long id, @RequestBody OptionRequest request) {
        questionService.updateOption(id, optionId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/option/{optionId}")
    public ResponseEntity<Void> deleteOption(@PathVariable(name = "id") Long id, @PathVariable(name = "optionId") Long optionId) {
        questionService.removeOption(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
