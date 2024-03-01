package com.alibou.security.quiz.article;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserQuiz {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "material_id")
    private Integer articleId;

    @Column(name = "test_id")
    private Long quizId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userQuiz")
    private List<UserQuizAnswer> answers;
}

