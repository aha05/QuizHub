package com.quiz.QuizHub.core.user_activity;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("user-activity")
@Tag(name = "UserActivity")
public class UserActivityController {

}
