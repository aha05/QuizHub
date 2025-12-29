package com.quiz.QuizHub.question;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Question")
public class QuestionController {
    private final QuestionService questionService;


    @Operation(summary = "Get all questions of all quizzes")
    @GetMapping("question")
    public ResponseEntity<List<QuestionResponse>> getQuestions() {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestions());
    }

    @Operation(summary = "Add a question to the quiz")
    @PostMapping("quiz/{quizId}/question")
    public ResponseEntity<QuestionResponse> addQuestion(@PathVariable(name = "quizId") Long quizId, @Valid @RequestBody QuestionRequest request) {

        return ResponseEntity.status(HttpStatus.OK).body(questionService.addQuestion(quizId, request));
    }

    @PutMapping("question/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(@Valid @RequestBody QuestionRequest request, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.updateQuestion(request, id));
    }

    @DeleteMapping("question/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        System.out.println("Delete request");
        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
