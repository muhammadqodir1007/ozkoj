package com.alibou.security.quiz.article;

import lombok.Data;

import java.util.List;
@Data
public class QuizDto {


    private String question;
    private List<String> options;
    private String correct;
    private Integer article;
}
