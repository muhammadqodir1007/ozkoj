package com.alibou.security.quiz.material;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_test_answer")
public class UserTestAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_test_id")
    private UserTest userTest;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "answer")
    private String answer;

    @Column(name = "correct")
    private String correct;

    // getters and setters
}
