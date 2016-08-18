package service;

import model.Article;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 *
 * Lists the methods implemented in service.impl.ArticleServiceImpl.java
 *
 * @version %I%, %G%
 */
public interface ArticleService {

    /**
     *
     * @param article the article to be saved
     * @return article is saved
     */
    int saveArticle(Article article);

    /**
     *
     * @param article the article to be updated
     * @return article is updated
     */
    Article updateArticle(Article article);

    /**
     *
     * @param title the title to find
     * @return the found article or null if none
     */
    Article findArticleByName(String title);

}
