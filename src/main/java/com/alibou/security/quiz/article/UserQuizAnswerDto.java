package com.alibou.security.quiz.article;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class UserQuizAnswerDto {

    private Long questionId;
    private String answer;
    private String correct;
}
