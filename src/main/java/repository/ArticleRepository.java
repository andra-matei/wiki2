package repository;

import model.Article;
import model.Word;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */
@Repository
public class ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @param article the article to be persisted
     */
    public void saveArticle(Article article) {
        entityManager.persist(article);
    }

    /**
     * @param article the article to be merged
     */
    public void updateArticle(Article article) {
        entityManager.merge(article);
    }

    /**
     * @param title the title of the article
     * @return the article with the title "title"
     * if no article is found then return nothing
     */
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
