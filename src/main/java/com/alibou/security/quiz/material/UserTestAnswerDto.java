package com.alibou.security.quiz.material;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTestAnswerDto {
    private Long questionId;
    private String answer;
    private String correct;
}
