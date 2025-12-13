package com.quiz.QuizHub.user;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("User already exist with this email or username.");
    }
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
