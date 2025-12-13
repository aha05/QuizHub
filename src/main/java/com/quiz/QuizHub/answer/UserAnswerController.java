package com.quiz.QuizHub.answer;

import com.quiz.QuizHub.score.ScoreResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserAnswerController {
    private UserAnswerService userAnswerService;

    @PostMapping("quiz/{quizId}/answer")
    public ResponseEntity<UserAnswerResponse> userAnswer(@PathVariable(name = "quizId") Long quizId, @RequestBody UserAnswerRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userAnswerService.userAnswer(quizId, request));
    }

    @GetMapping("score/{userId}")
    public ResponseEntity<List<ScoreResponse>> userScore(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userAnswerService.getUserScore(userId));
    }

    @GetMapping("user/{userId}/answer")
    public ResponseEntity<List<UserAnswerResponse>> userAnswer(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userAnswerService.getUserAnswer(userId));
    }
}
