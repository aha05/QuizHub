package com.quiz.QuizHub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Admin")
public class AdminController {
    @GetMapping("/hello")
    private String index(){
        return "hello this is admin";
    }
}
