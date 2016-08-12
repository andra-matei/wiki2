package service.impl;

import model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ArticleRepository;
import service.ArticleService;

/**
 * Created by azburatura on 8/11/2016.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void saveArticle(Article article) {
        articleRepository.saveArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.updateArticle(article);
    }

    @Override
    public Article findArticleByName(String title) {
        return articleRepository.findArticleByName(title);
    }

}
