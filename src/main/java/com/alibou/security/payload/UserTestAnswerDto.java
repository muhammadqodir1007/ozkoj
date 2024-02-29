package com.alibou.security.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTestAnswerDto {
    private Long questionId;
    private String answer;
    private String correct;
}
