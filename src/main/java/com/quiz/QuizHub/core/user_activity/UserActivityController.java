package com.quiz.QuizHub.core.user_activity;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
