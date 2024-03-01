package com.alibou.security.quiz.material;

import lombok.Data;

import java.util.List;

@Data
public class TestDto {

    private String question;
    private List<String> options;
    private String correct;
    private Integer material;


}
