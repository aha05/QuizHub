package com.quiz.QuizHub.option;


public class OptionNotFoundException extends RuntimeException {
    public OptionNotFoundException() {
        super("No option found.");
    }

    public OptionNotFoundException(String message) {
        super(message);
    }
}
