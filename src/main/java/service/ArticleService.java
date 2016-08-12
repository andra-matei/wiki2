package service;

import model.Article;

/**
 * Created by azburatura on 8/11/2016.
 */
public interface ArticleService {

    void saveArticle(Article article);

    void updateArticle(Article article);

    Article findArticleByName(String title);

}
