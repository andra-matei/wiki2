package service;

import model.Article;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 *         <p>
 *         Lists the methods implemented in service.impl.ArticleServiceImpl.java
 *         </p>
 * @version %I%, %G%
 */
public interface ArticleService {

    void saveArticle(Article article);

    void updateArticle(Article article);

    Article findArticleByName(String title);

}
