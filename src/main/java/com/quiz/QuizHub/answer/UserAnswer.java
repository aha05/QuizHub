package com.quiz.QuizHub.answer;

import com.quiz.QuizHub.option.Option;
import com.quiz.QuizHub.question.Question;
import com.quiz.QuizHub.quiz.Quiz;
import com.quiz.QuizHub.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_answers")
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private Option option;

    private boolean correct;

    public static UserAnswer create(
            User user,
            Quiz quiz,
            Question question,
            Option option
    ) {
        UserAnswer answer = new UserAnswer();
        answer.user = user;
        answer.quiz = quiz;
        answer.question = question;
        answer.option = option;
        answer.correct = option.isCorrect();
        return answer;
    }
}
