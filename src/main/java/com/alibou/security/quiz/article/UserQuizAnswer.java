package com.alibou.security.quiz.article;

import com.alibou.security.quiz.material.UserTest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_quiz_answer")
public class UserQuizAnswer {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_quiz_id")
    private UserQuiz userQuiz;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "answer")
    private String answer;

    @Column(name = "correct")
    private String correct;

}
