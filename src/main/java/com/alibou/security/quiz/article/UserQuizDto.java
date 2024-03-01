package com.alibou.security.quiz.article;

import lombok.Data;

import java.util.List;

@Data
public class UserQuizDto {

    private Long userId;
    private Integer articleId;
    private Long quizId;
    private List<UserQuizAnswerDto> answers;
}
