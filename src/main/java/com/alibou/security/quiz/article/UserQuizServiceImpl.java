package com.alibou.security.quiz.article;

import com.alibou.security.entity.Article;
import com.alibou.security.exceptions.NotFoundException;
import com.alibou.security.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserQuizServiceImpl implements UserQuizService {

    private final UserQuizRepository userQuizRepository;
    private final ArticleRepository articleRepository;

    @Override
    public List<UserQuiz> findAll() {
        return userQuizRepository.findAll();
    }

    @Override
    public UserQuiz findById(Long id) {
        return userQuizRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public UserQuiz create(UserQuizDto userQuizDto) {
        Article article = articleRepository.findById(userQuizDto.getArticleId()).orElseThrow(NotFoundException::new);

        UserQuiz userQuiz = UserQuiz.builder()
                .userId(userQuizDto.getUserId())
                .articleId(userQuizDto.getArticleId())
                .quizId(userQuizDto.getQuizId())
                .answers(mapToUserQuizAnswerList(userQuizDto.getAnswers()))
                .build();

        return userQuizRepository.save(userQuiz);
    }

    @Override
    public UserQuiz update(Long userQuizId, UserQuizDto userQuizDto) {
        UserQuiz existingUserQuiz = userQuizRepository.findById(userQuizId).orElseThrow(NotFoundException::new);

        existingUserQuiz.setUserId(userQuizDto.getUserId());
        existingUserQuiz.setArticleId(userQuizDto.getArticleId());
        existingUserQuiz.setQuizId(userQuizDto.getQuizId());
        existingUserQuiz.setAnswers(mapToUserQuizAnswerList(userQuizDto.getAnswers()));

        return userQuizRepository.save(existingUserQuiz);
    }

    @Override
    public void delete(Long id) {
        userQuizRepository.deleteById(id);
    }

    private List<UserQuizAnswer> mapToUserQuizAnswerList(List<UserQuizAnswerDto> answerDtos) {
        return answerDtos.stream()
                .map(answerDto -> UserQuizAnswer.builder()
                        .questionId(answerDto.getQuestionId())
                        .answer(answerDto.getAnswer())
                        .correct(answerDto.getCorrect())
                        .build())
                .collect(Collectors.toList());
    }
}
