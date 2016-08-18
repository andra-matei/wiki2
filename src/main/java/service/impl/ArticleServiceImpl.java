package service.impl;

import model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ArticleRepository;
import service.ArticleService;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    /**
     * Injected ArticleRepository bean
     */
    @Autowired
    private ArticleRepository articleRepository;

    /*
     * (non-Javadoc)
	 *
	 * @see repository.ArticleRepository.java
	 */
    @Override
    public int saveArticle(Article article) {
        return articleRepository.saveArticle(article);
    }

    /*
     * (non-Javadoc)
	 *
	 * @see repository.ArticleRepository.java
	 */
    @Override
    public Article updateArticle(Article article) {
       return articleRepository.updateArticle(article);
    }

    /*
     * (non-Javadoc)
	 *
	 * @see repository.ArticleRepository.java
	 */
    @Override
    public Article findArticleByName(String title) {
        return articleRepository.findArticleByName(title);
    }

}
