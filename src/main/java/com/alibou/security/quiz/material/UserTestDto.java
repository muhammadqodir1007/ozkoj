package com.alibou.security.quiz.material;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserTestDto {
    private Long userId;
    private Long materialId;
    private Long testId;
    private List<UserTestAnswerDto> answers;
}
