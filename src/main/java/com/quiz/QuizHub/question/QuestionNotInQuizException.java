package com.quiz.QuizHub.question;

public class QuestionNotInQuizException extends RuntimeException {
    public QuestionNotInQuizException() {
        super("Question does not belong to the specified quiz");
    }
    public QuestionNotInQuizException(String message) {
        super(message);
    }
}
