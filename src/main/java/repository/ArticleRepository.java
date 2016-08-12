package repository;

import model.Article;
import model.Word;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by azburatura on 8/11/2016.
 */
@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveArticle(Article article) {
        entityManager.persist(article);
    }

    public void updateArticle(Article article) {
        entityManager.merge(article);
    }

    public Article findArticleByName(String title) {
        Query query = entityManager.createQuery("select a from Article a where a.title = :title");
        List<Article> articleList = query.setParameter("title", title).getResultList();
        if (!articleList.isEmpty()) {
            Article article = articleList.get(0);
            return article;
        }
        return null;
    }
}
