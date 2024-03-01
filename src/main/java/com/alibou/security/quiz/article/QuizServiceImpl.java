package com.alibou.security.quiz.article;

import com.alibou.security.entity.Article;
import com.alibou.security.exceptions.NotFoundException;
import com.alibou.security.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final ArticleRepository articleRepository;

    @Override
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz findById(Long id) {
        return quizRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Quiz create(QuizDto resource) throws IOException {
        Article article = articleRepository.findById(resource.getArticle()).orElseThrow(NotFoundException::new);

        Quiz build = Quiz.builder()
                .createdDate(LocalDateTime.now())
                .question(resource.getQuestion())
                .options(resource.getOptions())
                .correct(resource.getCorrect())
                .material(article)
                .build();

        return quizRepository.save(build);
    }

    @Override
    public Quiz update(Long resourceId, QuizDto resource) throws IOException {
        Quiz quiz = quizRepository.findById(resourceId).orElseThrow(NotFoundException::new);
        Quiz updatedQuiz = updateQuiz(quiz, resource);
        return quizRepository.save(updatedQuiz);
    }

    private Quiz updateQuiz(Quiz oldQuiz, QuizDto newQuiz) {
        if (newQuiz.getCorrect() != null) {
            oldQuiz.setCorrect(newQuiz.getCorrect());
        }
        if (newQuiz.getOptions() != null) {
            oldQuiz.setOptions(newQuiz.getOptions());
        }
        if (newQuiz.getQuestion() != null) {
            oldQuiz.setQuestion(newQuiz.getQuestion());
        }
        return oldQuiz;
    }

    @Override
    public void delete(Long id) throws IOException {
        quizRepository.deleteById(id);
    }
}
