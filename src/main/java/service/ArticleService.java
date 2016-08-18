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

    int saveArticle(Article article);

    Article updateArticle(Article article);

    Article findArticleByName(String title);

}
