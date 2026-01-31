package com.quiz.QuizHub.user_activity;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("user-activity")
@Tag(name = "UserActivity")
public class UserActivityController {
    private final UserActivityService userActivityService;

    @GetMapping("/history")
    public ResponseEntity<List<History>> getHistory() {
        return ResponseEntity.status(HttpStatus.OK).body(userActivityService.getHistory());
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<Leaderboard>> getLeaderboard() {
        return ResponseEntity.status(HttpStatus.OK).body(userActivityService.getLeaderboard());
    }

    @GetMapping
    public ResponseEntity<UserActivityDto> getUserActivity() {
        return ResponseEntity.status(HttpStatus.OK).body(userActivityService.getUserActivity());
    }
}
