package com.quiz.QuizHub.quiz;


public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException() {
        super("Quiz not found.");
    }

    public QuizNotFoundException(String message) {
        super(message);
    }
}
