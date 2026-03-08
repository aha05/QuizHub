package com.quiz.QuizHub.submission;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizResultController {
    private final QuizResultService quizResultService;

    @PostMapping("/{quizId}/submit")
    public ResponseEntity<?> submitQuiz(@PathVariable Long quizId, @Valid @RequestBody SubmitQuizRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(quizResultService.submitQuiz(quizId, request));
    }

    @GetMapping("/{userId}/history")
    public ResponseEntity<List<QuizResult>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(quizResultService.getHistory(userId));
    }

    @GetMapping("/{quizId}/bestResult")
    public ResponseEntity<QuizHistoryDto> getBestResult(@PathVariable Long quizId) {
        return ResponseEntity.status(HttpStatus.OK).body(quizResultService.getBestResult(quizId));
    }

    @GetMapping("/quizResult/{id}")
    public ResponseEntity<QuizResult> getBestResultById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(quizResultService.getHistoryById(id));
    }
}
