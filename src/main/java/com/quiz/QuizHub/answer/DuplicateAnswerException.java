package com.quiz.QuizHub.answer;

public class DuplicateAnswerException extends RuntimeException {
    public DuplicateAnswerException() {
        super("Duplicate answer.");
    }

    public DuplicateAnswerException(String message) {
        super(message);
    }
}
