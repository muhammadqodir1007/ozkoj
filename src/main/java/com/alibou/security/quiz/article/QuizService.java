package com.alibou.security.quiz.article;

import java.io.IOException;
import java.util.List;

public interface QuizService {


    List<Quiz> findAll();
    List<Quiz> findAllByArticleId(int id);

    Quiz findById(Long id);

    Quiz create(QuizDto resource) throws IOException;

    Quiz update(Long resourceId, QuizDto resource) throws IOException;

    void delete(Long id) throws IOException;
}
