package service;

import model.Article;
import model.Word;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by azburatura on 8/18/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:webapp/WEB-INF/mvc-dispatcher-servlet.xml",
                        "file:webapp/WEB-INF/spring-context.xml" })
@EnableWebMvc
@Transactional
public class WordServiceTest {

    @Autowired
    private WordService wordService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testSaveWordService() {
        Word word = new Word();
        word.setName("TestWord");
        word.setOccurrences(4);
        Article article = new Article();
        article.setTitle("TestArticle");
        article.setUrl("TestArticleUrl");
        entityManager.persist(article);
        word.setArticle(article);

        int id = wordService.saveWord(word);

        Assert.assertNotEquals(id, 0);
    }

}
