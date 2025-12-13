package com.quiz.QuizHub.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    @GetMapping("/hello")
    private String index(){
        return "hello this is admin";
    }
}
