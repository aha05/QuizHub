package com.quiz.QuizHub.option;

public class OptionNotInQuestionException extends RuntimeException {
    public OptionNotInQuestionException() {
        super("Option does not belong to the specified question");
    }
    public OptionNotInQuestionException(String message) {
        super(message);
    }
}
