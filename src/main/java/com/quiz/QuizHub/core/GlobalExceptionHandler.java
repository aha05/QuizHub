package com.quiz.QuizHub.core;

import com.quiz.QuizHub.option.OptionNotFoundException;
import com.quiz.QuizHub.option.OptionNotInQuestionException;
import com.quiz.QuizHub.question.QuestionNotFoundException;
import com.quiz.QuizHub.question.QuestionNotInQuizException;
import com.quiz.QuizHub.quiz.QuizNotFoundException;
import com.quiz.QuizHub.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException exception
    ){
        var errors = new HashMap<String, String>();

        exception.getBindingResult().getFieldErrors().forEach( error ->{
                    errors.put(error.getField(), error.getDefaultMessage());
                }
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ErrorDto> handleQuestionNotFound(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ErrorDto> handleQuizNotFound(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(OptionNotFoundException.class)
    public ResponseEntity<ErrorDto> handleOptionNotFound(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFound(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(OptionNotInQuestionException.class)
    public ResponseEntity<ErrorDto> handleOptionNotInQuestion(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(QuestionNotInQuizException.class)
    public ResponseEntity<ErrorDto> handleQuestionNotInQuiz(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorDto(ex.getMessage()));
    }
}

