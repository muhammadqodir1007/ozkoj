package com.alibou.security.payload;

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
